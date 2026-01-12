package de.kennyhml.e4.abap_highlighter;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.osgi.framework.Bundle;
import org.treesitter.TSLanguage;
import org.treesitter.TSParser;
import org.treesitter.TSQuery;
import org.treesitter.TSQueryCapture;
import org.treesitter.TSQueryCursor;
import org.treesitter.TSQueryMatch;
import org.treesitter.TSTree;

import java.io.InputStream;

/*
 * An Adapter to turn the syntax tree produced by the Treesitter Grammar into the
 * TextPresentation format that Eclipse uses.
 */
public class TsPresentationAdapter implements IPresentationRepairer {

	private IDocument fDocument;
	private TSParser fParser;
	private TSQuery fQuery;
	private TSLanguage fLanguage;

	public TsPresentationAdapter() {
		fParser = new TSParser();
		fLanguage = new TreeSitterAbap();
		fParser.setLanguage(fLanguage);

		Bundle bundle = Platform.getBundle("de.kennyhml.e4.abap_highlighter");
		URL fileUrl = FileLocator.find(bundle, new Path("external/queries/highlights.scm"), null);

		if (fileUrl != null) {
			try (InputStream is = fileUrl.openStream(); Scanner scanner = new Scanner(is, "UTF-8")) {
				String query = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
				// query = query.replace("\r\n", " ").replace("\r", " ").replace("\n", " ");
				ILog.get().warn(query);
				fQuery = new TSQuery(fLanguage, query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setDocument(IDocument document) {
		fDocument = document;
	}

	@Override
	public void createPresentation(TextPresentation presentation, ITypedRegion region) {
		TSTree tree = fParser.parseString(null, fDocument.get());
		
		TSQueryCursor cursor = new TSQueryCursor();
		cursor.exec(fQuery, tree.getRootNode());
		ILog.get().info("Source: " + fDocument.get());
		
		TSQueryMatch match = new TSQueryMatch();
        while(cursor.nextMatch(match)){
        	for (TSQueryCapture capture: match.getCaptures()) {
        		String captureName = fQuery.getCaptureNameForId(capture.getIndex());
                
                int start = capture.getNode().getStartByte();
                int end = capture.getNode().getEndByte();
                String capturedText = fDocument.get().substring(start, end);
                ILog.get().info("Capture: @" + captureName + " | Text: " + capturedText);
        	}
        }
	}
}