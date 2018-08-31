apttools
========

Simple library to help annotation processing programs. The very first version of the library was developed to support the fluflu annotation tool. Later versions already use the fluflu annotation tool. This is an interesting approach, since you need a version of fluflu to compile the library apttools, and on the other hand fluflu depends on apttools.

Because fluflu is an annotation processor that is executed dring run time and is not a library to be included during run time this is not a circular dependency.
