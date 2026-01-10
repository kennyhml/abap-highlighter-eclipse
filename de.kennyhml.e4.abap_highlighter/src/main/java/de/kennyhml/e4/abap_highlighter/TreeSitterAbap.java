package de.kennyhml.e4.abap_highlighter;

import org.treesitter.TSLanguage;
import org.treesitter.utils.NativeUtils;

public class TreeSitterAbap extends TSLanguage {

    static {
        NativeUtils.loadLib("lib/tree-sitter-abap");
    }
    private native static long tree_sitter_abap();

    public TreeSitterAbap() {
        super(tree_sitter_abap());
    }

    private TreeSitterAbap(long ptr) {
        super(ptr);
    }

    @Override
    public TSLanguage copy() {
        return new TreeSitterAbap(copyPtr());
    }


}
