# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
#   flexdll/LICENSE
#   manual/LICENSE-for-the-manual
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4f72f33f302a53dc329f4d3819fe14f9 \
                    file://manual/LICENSE-for-the-manual;md5=92bb97903538bfb5e218d7937bf8e607"

SRC_URI = "git://github.com/ocaml/ocaml;branch=5.2;protocol=https"

# Modify these as desired
PV = "5.2+git"
SRCREV = "7c6418d86cdeb9ee1afbcd65f49d40df0fefe236"

S = "${WORKDIR}/git"
BBEXTENDCLASS = "native nativesdk"

# NOTE: the following prog dependencies are unknown, ignoring: diff csc rlwrap patdiff flexlink
DEPENDS = "zstd"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit native

FILES:${PN} = "${bindir}/ocaml ${bindir}/ocamlc ${bindir}/ocamllex ${bindir}/ocamlyacc"

do_configure () {
    ./configure
}

do_compile () {
    oe_runmake
}

do_install () {
    #install -d ${D}${bindir}
    #install -m 0755 ${S}/ocaml ${D}${bindir}
    #install -m 0755 ${S}/runtime/ocamlrun ${D}${bindir}
    #install -m 0755 ${S}/ocamlc ${D}${bindir}
    #install -m 0755 ${S}/lex/ocamllex ${D}${bindir}
    #install -m 0755 ${S}/yacc/ocamlyacc ${D}${bindir}

    #install -d ${D}${libdir}/stublibs
    #install -d ${D}${libdir}/ocaml
    #cp -r ${S}/stdlib ${D}${libdir}/stublibs
    #cp -r ${S}/ocaml ${D}${libdir}/ocaml

    oe_runmake install DESTDIR=${D} BINDIR=${bindir} LIBDIR=${libdir}/ocaml
    ls ${D}
}

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = ""

