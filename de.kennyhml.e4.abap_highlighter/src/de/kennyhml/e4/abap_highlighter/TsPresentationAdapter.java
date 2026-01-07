package de.kennyhml.e4.abap_highlighter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationRepairer;

/*
 * An Adapter to turn the syntax tree produced by the Treesitter Grammar into the
 * TextPresentation format that Eclipse uses.
 */
public class TsPresentationAdapter implements IPresentationRepairer {

	protected IDocument fDocument;
	
	@Override
	public void setDocument(IDocument document) {
		fDocument = document;
	}
	
	@Override
	public void createPresentation(TextPresentation presentation, ITypedRegion region) {
		// im guessing we have to write to the passed presentation?
	}
}