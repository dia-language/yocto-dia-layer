# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/dia-language/dia-legacy;branch=main;protocol=https;rev=3a8baefbce74e46c13e22a3e070853cf2137b99d"

S = "${WORKDIR}/sources-unpack/git"

DEPENDS = "flex-native bison-native"

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""

FILES:${PN} = "${bindir}/diac-legacy /home/root/example"

do_install() {
    ls ${S}

    if [ -e ${S}/lex.yy.c ]; then
        rm ${S}/lex.yy.c
    fi

    if [ -e ${S}/dia.tab.c ]; then
        rm ${S}/dia.tab.c
    fi

    if [ -e ${S}/dia.tab.h ]; then
        rm ${S}/dia.tab.h
    fi

    install -d ${D}${bindir}
    install --help
    install -m 0755 ${B}/diac ${D}${bindir}/diac-legacy

    install -d ${D}/home/root
    cp -r ${S}/example ${D}/home/root
}

