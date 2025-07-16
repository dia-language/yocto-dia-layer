# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "GPL-3-with-bison-exception"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/dia-language/dia;branch=main;protocol=https"

# Modify these as desired
PV = "git.new+git"
SRCREV = "bfe75f523a8d2521ffda8adffb67c5478640749d"

S = "${WORKDIR}/git"

FILES:${PN} = "${bindir}/diac"

DEPENDS += "ocaml-native"
INSANE_SKIP:${PN}-dbg += "buildpaths"

EXTRA_OEMAKE:append = " OCAMLCOMP='ocamlopt -cclib '-static' -I ../recipe-sysroot-native/usr/lib/ocaml -ccopt -Wl,--dynamic-linker=/lib/ld-linux.so.2' "
# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
  mkdir -p ${D}${bindir}
  mkdir -p ${D}${libdir}
  cp -r ${STAGING_LIBDIR_NATIVE}/ocaml ${D}${libdir}
  cp ${STAGING_BINDIR_NATIVE}/ocamlopt ${D}${bindir}
  cp ${STAGING_BINDIR_NATIVE}/ocamllex ${D}${bindir}
  cp ${STAGING_BINDIR_NATIVE}/ocamlyacc ${D}${bindir}
	# You will almost certainly need to add additional arguments here
	oe_runmake

  ls ${libdir}
}

do_install () {
	# NOTE: unable to determine what to put here - there is a Makefile but no
	# target named "install", so you will need to define this yourself
  :
  install -d ${D}${bindir}
  install -m 0755 ${B}/diac ${D}${bindir}
}

