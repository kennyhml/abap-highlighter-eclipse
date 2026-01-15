# ABAP Highlighter Eclipse
A work-in-progress plugin for the [Eclipse IDE](eclipse) that modifies the ABAP Development Tools ([ADT](adt)) to provide enhanced syntax highlighting capabilities for the ABAP programming language.

Parsing and subsequent tokenization is powered by the [tree-sitter-abap](ts_abap) grammar.

## Building (for development)
### Prerequisites
- JDK21+
- Maven (Tycho 5.0.0 compatible)
- Zig (0.11.0+)

[eclipse]: https://www.eclipse.org/
[adt]: https://help.sap.com/docs/btp/sap-business-technology-platform/eclipse-tool-for-abap-environment
[ts_abap]: https://github.com/kennyhml/tree-sitter-abap
