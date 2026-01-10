#include <jni.h>
#include <stdint.h>

void *tree_sitter_abap();
/*
 * Class:     org_treesitter_TreeSitterAbap
 * Method:    tree_sitter_abap
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_de_kennyhml_e4_abap_1highlighter_TreeSitterAbap_tree_1sitter_1abap
  (JNIEnv *env, jclass clz){
   return (jlong)(intptr_t)tree_sitter_abap();
}