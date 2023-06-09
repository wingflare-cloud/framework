package com.wingflare.lib.core.enums;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description MediaType
 */
public enum MediaTypes {

    EZ("ez", "application", "andrew-inset"),
    ANX("anx", "application", "annodex"),
    ATOM("atom", "application", "atom+xml"),
    ATOMCAT("atomcat", "application", "atomcat+xml"),
    ATOMSRV("atomsrv", "application", "atomserv+xml"),
    LIN("lin", "application", "bbolin"),
    CU("cu", "application", "cu-seeme"),
    DAVMOUNT("davmount", "application", "davmount+xml"),
    DCM("dcm", "application", "dicom"),
    TSP("tsp", "application", "dsptype"),
    ES("es", "application", "ecmascript"),
    SPL("spl", "application", "futuresplash"),
    HTA("hta", "application", "hta"),
    JAR("jar", "application", "java-archive"),
    SER("ser", "application", "java-serialized-object"),
    CLASS("class", "application", "java-vm"),
    JS("js", "application", "javascript"),
    JSON("json", "application", "json"),
    M3G("m3g", "application", "m3g"),
    HQX("hqx", "application", "mac-binhex40"),
    CPT("cpt", "application", "mac-compactpro"),
    NB("nb", "application", "mathematica"),
    NBP("nbp", "application", "mathematica"),
    MBOX("mbox", "application", "mbox"),
    MDB("mdb", "application", "msaccess"),
    DOC("doc", "application", "msword"),
    DOT("dot", "application", "msword"),
    MXF("mxf", "application", "mxf"),
    BIN("bin", "application", "octet-stream"),
    ODA("oda", "application", "oda"),
    OGX("ogx", "application", "ogg"),
    ONE("one", "application", "onenote"),
    ONETOC2("onetoc2", "application", "onenote"),
    ONETMP("onetmp", "application", "onenote"),
    ONEPKG("onepkg", "application", "onenote"),
    PDF("pdf", "application", "pdf"),
    PGP("pgp", "application", "pgp-encrypted"),
    KEY("key", "application", "pgp-keys"),
    SIG("sig", "application", "pgp-signature"),
    PRF("prf", "application", "pics-rules"),
    PS("ps", "application", "postscript"),
    AI("ai", "application", "postscript"),
    EPS("eps", "application", "postscript"),
    EPSI("epsi", "application", "postscript"),
    EPSF("epsf", "application", "postscript"),
    EPS2("eps2", "application", "postscript"),
    EPS3("eps3", "application", "postscript"),
    RAR("rar", "application", "rar"),
    RDF("rdf", "application", "rdf+xml"),
    RTF("rtf", "application", "rtf"),
    STL("stl", "application", "sla"),
    SMI("smi", "application", "smil+xml"),
    SMIL("smil", "application", "smil+xml"),
    XHTML("xhtml", "application", "xhtml+xml"),
    XHT("xht", "application", "xhtml+xml"),
    XML("xml", "application", "xml"),
    XSD("xsd", "application", "xml"),
    XSL("xsl", "application", "xslt+xml"),
    XSLT("xslt", "application", "xslt+xml"),
    XSPF("xspf", "application", "xspf+xml"),
    ZIP("zip", "application", "zip"),
    APK("apk", "application", "vnd.android.package-archive"),
    CDY("cdy", "application", "vnd.cinderella"),
    KML("kml", "application", "vnd.google-earth.kml+xml"),
    KMZ("kmz", "application", "vnd.google-earth.kmz"),
    XUL("xul", "application", "vnd.mozilla.xul+xml"),
    XLS("xls", "application", "vnd.ms-excel"),
    XLB("xlb", "application", "vnd.ms-excel"),
    XLT("xlt", "application", "vnd.ms-excel"),
    XLAM("xlam", "application", "vnd.ms-excel.addin.macroEnabled.12"),
    XLSB("xlsb", "application", "vnd.ms-excel.sheet.binary.macroEnabled.12"),
    XLSM("xlsm", "application", "vnd.ms-excel.sheet.macroEnabled.12"),
    XLTM("xltm", "application", "vnd.ms-excel.template.macroEnabled.12"),
    EOT("eot", "application", "vnd.ms-fontobject"),
    THMX("thmx", "application", "vnd.ms-officetheme"),
    CAT("cat", "application", "vnd.ms-pki.seccat"),
    PPT("ppt", "application", "vnd.ms-powerpoint"),
    PPS("pps", "application", "vnd.ms-powerpoint"),
    PPAM("ppam", "application", "vnd.ms-powerpoint.addin.macroEnabled.12"),
    PPTM("pptm", "application", "vnd.ms-powerpoint.presentation.macroEnabled.12"),
    SLDM("sldm", "application", "vnd.ms-powerpoint.slide.macroEnabled.12"),
    PPSM("ppsm", "application", "vnd.ms-powerpoint.slideshow.macroEnabled.12"),
    POTM("potm", "application", "vnd.ms-powerpoint.template.macroEnabled.12"),
    DOCM("docm", "application", "vnd.ms-word.document.macroEnabled.12"),
    DOTM("dotm", "application", "vnd.ms-word.template.macroEnabled.12"),
    ODC("odc", "application", "vnd.oasis.opendocument.chart"),
    ODB("odb", "application", "vnd.oasis.opendocument.database"),
    ODF("odf", "application", "vnd.oasis.opendocument.formula"),
    ODG("odg", "application", "vnd.oasis.opendocument.graphics"),
    OTG("otg", "application", "vnd.oasis.opendocument.graphics-template"),
    ODI("odi", "application", "vnd.oasis.opendocument.image"),
    ODP("odp", "application", "vnd.oasis.opendocument.presentation"),
    OTP("otp", "application", "vnd.oasis.opendocument.presentation-template"),
    ODS("ods", "application", "vnd.oasis.opendocument.spreadsheet"),
    OTS("ots", "application", "vnd.oasis.opendocument.spreadsheet-template"),
    ODT("odt", "application", "vnd.oasis.opendocument.text"),
    ODM("odm", "application", "vnd.oasis.opendocument.text-master"),
    OTT("ott", "application", "vnd.oasis.opendocument.text-template"),
    OTH("oth", "application", "vnd.oasis.opendocument.text-web"),
    PPTX("pptx", "application", "vnd.openxmlformats-officedocument.presentationml.presentation"),
    SLDX("sldx", "application", "vnd.openxmlformats-officedocument.presentationml.slide"),
    PPSX("ppsx", "application", "vnd.openxmlformats-officedocument.presentationml.slideshow"),
    POTX("potx", "application", "vnd.openxmlformats-officedocument.presentationml.template"),
    XLSX("xlsx", "application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    XLTX("xltx", "application", "vnd.openxmlformats-officedocument.spreadsheetml.template"),
    DOCX("docx", "application", "vnd.openxmlformats-officedocument.wordprocessingml.document"),
    DOTX("dotx", "application", "vnd.openxmlformats-officedocument.wordprocessingml.template"),
    COD("cod", "application", "vnd.rim.cod"),
    MMF("mmf", "application", "vnd.smaf"),
    SDC("sdc", "application", "vnd.stardivision.calc"),
    SDS("sds", "application", "vnd.stardivision.chart"),
    SDA("sda", "application", "vnd.stardivision.draw"),
    SDD("sdd", "application", "vnd.stardivision.impress"),
    SDF("sdf", "application", "vnd.stardivision.math"),
    SDW("sdw", "application", "vnd.stardivision.writer"),
    SGL("sgl", "application", "vnd.stardivision.writer-global"),
    SXC("sxc", "application", "vnd.sun.xml.calc"),
    STC("stc", "application", "vnd.sun.xml.calc.template"),
    SXD("sxd", "application", "vnd.sun.xml.draw"),
    STD("std", "application", "vnd.sun.xml.draw.template"),
    SXI("sxi", "application", "vnd.sun.xml.impress"),
    STI("sti", "application", "vnd.sun.xml.impress.template"),
    SXM("sxm", "application", "vnd.sun.xml.math"),
    SXW("sxw", "application", "vnd.sun.xml.writer"),
    SXG("sxg", "application", "vnd.sun.xml.writer.global"),
    STW("stw", "application", "vnd.sun.xml.writer.template"),
    SIS("sis", "application", "vnd.symbian.install"),
    CAP("cap", "application", "vnd.tcpdump.pcap"),
    PCAP("pcap", "application", "vnd.tcpdump.pcap"),
    VSD("vsd", "application", "vnd.visio"),
    WBXML("wbxml", "application", "vnd.wap.wbxml"),
    WMLC("wmlc", "application", "vnd.wap.wmlc"),
    WMLSC("wmlsc", "application", "vnd.wap.wmlscriptc"),
    WPD("wpd", "application", "vnd.wordperfect"),
    WP5("wp5", "application", "vnd.wordperfect5.1"),
    WK("wk", "application", "x-123"),
    _7Z("7z", "application", "x-7z-compressed"),
    ABW("abw", "application", "x-abiword"),
    DMG("dmg", "application", "x-apple-diskimage"),
    BCPIO("bcpio", "application", "x-bcpio"),
    TORRENT("torrent", "application", "x-bittorrent"),
    CAB("cab", "application", "x-cab"),
    CBR("cbr", "application", "x-cbr"),
    CBZ("cbz", "application", "x-cbz"),
    CDF("cdf", "application", "x-cdf"),
    CDA("cda", "application", "x-cdf"),
    VCD("vcd", "application", "x-cdlink"),
    PGN("pgn", "application", "x-chess-pgn"),
    MPH("mph", "application", "x-comsol"),
    CPIO("cpio", "application", "x-cpio"),
    CSH("csh", "application", "x-csh"),
    DEB("deb", "application", "x-debian-package"),
    UDEB("udeb", "application", "x-debian-package"),
    DCR("dcr", "application", "x-director"),
    DIR("dir", "application", "x-director"),
    DXR("dxr", "application", "x-director"),
    DMS("dms", "application", "x-dms"),
    WAD("wad", "application", "x-doom"),
    DVI("dvi", "application", "x-dvi"),
    PFA("pfa", "application", "x-font"),
    PFB("pfb", "application", "x-font"),
    GSF("gsf", "application", "x-font"),
    PCF("pcf", "application", "x-font"),
    PCF_Z("pcf.z", "application", "x-font"),
    WOFF("woff", "application", "x-font-woff"),
    MM("mm", "application", "x-freemind"),
    GAN("gan", "application", "x-ganttproject"),
    GNUMERIC("gnumeric", "application", "x-gnumeric"),
    SGF("sgf", "application", "x-go-sgf"),
    GCF("gcf", "application", "x-graphing-calculator"),
    GTAR("gtar", "application", "x-gtar"),
    TGZ("tgz", "application", "x-gtar-compressed"),
    TAZ("taz", "application", "x-gtar-compressed"),
    HDF("hdf", "application", "x-hdf"),
    HWP("hwp", "application", "x-hwp"),
    ICA("ica", "application", "x-ica"),
    INFO("info", "application", "x-info"),
    INS("ins", "application", "x-internet-signup"),
    ISP("isp", "application", "x-internet-signup"),
    III("iii", "application", "x-iphone"),
    ISO("iso", "application", "x-iso9660-image"),
    JAM("jam", "application", "x-jam"),
    JNLP("jnlp", "application", "x-java-jnlp-file"),
    JMZ("jmz", "application", "x-jmol"),
    CHRT("chrt", "application", "x-kchart"),
    KIL("kil", "application", "x-killustrator"),
    SKP("skp", "application", "x-koan"),
    SKD("skd", "application", "x-koan"),
    SKT("skt", "application", "x-koan"),
    SKM("skm", "application", "x-koan"),
    KPR("kpr", "application", "x-kpresenter"),
    KPT("kpt", "application", "x-kpresenter"),
    KSP("ksp", "application", "x-kspread"),
    KWD("kwd", "application", "x-kword"),
    KWT("kwt", "application", "x-kword"),
    LATEX("latex", "application", "x-latex"),
    LHA("lha", "application", "x-lha"),
    LYX("lyx", "application", "x-lyx"),
    LZH("lzh", "application", "x-lzh"),
    LZX("lzx", "application", "x-lzx"),
    FRM("frm", "application", "x-maker"),
    MAKER("maker", "application", "x-maker"),
    FRAME("frame", "application", "x-maker"),
    FM("fm", "application", "x-maker"),
    FB("fb", "application", "x-maker"),
    BOOK("book", "application", "x-maker"),
    FBDOC("fbdoc", "application", "x-maker"),
    MD5("md5", "application", "x-md5"),
    MIF("mif", "application", "x-mif"),
    M3U8("m3u8", "application", "x-mpegURL"),
    WMD("wmd", "application", "x-ms-wmd"),
    WMZ("wmz", "application", "x-ms-wmz"),
    COM("com", "application", "x-msdos-program"),
    EXE("exe", "application", "x-msdos-program"),
    BAT("bat", "application", "x-msdos-program"),
    DLL("dll", "application", "x-msdos-program"),
    MSI("msi", "application", "x-msi"),
    NC("nc", "application", "x-netcdf"),
    PAC("pac", "application", "x-ns-proxy-autoconfig"),
    DAT("dat", "application", "x-ns-proxy-autoconfig"),
    NWC("nwc", "application", "x-nwc"),
    O("o", "application", "x-object"),
    OZA("oza", "application", "x-oz-application"),
    P7R("p7r", "application", "x-pkcs7-certreqresp"),
    CRL("crl", "application", "x-pkcs7-crl"),
    PYC("pyc", "application", "x-python-code"),
    PYO("pyo", "application", "x-python-code"),
    QGS("qgs", "application", "x-qgis"),
    SHP("shp", "application", "x-qgis"),
    SHX("shx", "application", "x-qgis"),
    QTL("qtl", "application", "x-quicktimeplayer"),
    RDP("rdp", "application", "x-rdp"),
    RPM("rpm", "application", "x-redhat-package-manager"),
    RSS("rss", "application", "x-rss+xml"),
    RB("rb", "application", "x-ruby"),
    SCI("sci", "application", "x-scilab"),
    SCE("sce", "application", "x-scilab"),
    XCOS("xcos", "application", "x-scilab-xcos"),
    SH("sh", "application", "x-sh"),
    SHA1("sha1", "application", "x-sha1"),
    SHAR("shar", "application", "x-shar"),
    SWF("swf", "application", "x-shockwave-flash"),
    SWFL("swfl", "application", "x-shockwave-flash"),
    SCR("scr", "application", "x-silverlight"),
    SQL("sql", "application", "x-sql"),
    SIT("sit", "application", "x-stuffit"),
    SITX("sitx", "application", "x-stuffit"),
    SV4CPIO("sv4cpio", "application", "x-sv4cpio"),
    SV4CRC("sv4crc", "application", "x-sv4crc"),
    TAR("tar", "application", "x-tar"),
    TCL("tcl", "application", "x-tcl"),
    GF("gf", "application", "x-tex-gf"),
    PK("pk", "application", "x-tex-pk"),
    TEXINFO("texinfo", "application", "x-texinfo"),
    TEXI("texi", "application", "x-texinfo"),
    TILDE("~", "application", "x-trash"),
    PERCENT_SIGN("%", "application", "x-trash"),
    BAK("bak", "application", "x-trash"),
    OLD("old", "application", "x-trash"),
    SIK("sik", "application", "x-trash"),
    T("t", "application", "x-troff"),
    TR("tr", "application", "x-troff"),
    ROFF("roff", "application", "x-troff"),
    MAN("man", "application", "x-troff-man"),
    ME("me", "application", "x-troff-me"),
    MS("ms", "application", "x-troff-ms"),
    USTAR("ustar", "application", "x-ustar"),
    SRC("src", "application", "x-wais-source"),
    WZ("wz", "application", "x-wingz"),
    CRT("crt", "application", "x-x509-ca-cert"),
    XCF("xcf", "application", "x-xcf"),
    FIG("fig", "application", "x-xfig"),
    XPI("xpi", "application", "x-xpinstall"),
    AMR("amr", "audio", "amr"),
    AWB("awb", "audio", "amr-wb"),
    AXA("axa", "audio", "annodex"),
    AU("au", "audio", "basic"),
    SND("snd", "audio", "basic"),
    CSD("csd", "audio", "csound"),
    ORC("orc", "audio", "csound"),
    SCO("sco", "audio", "csound"),
    FLAC("flac", "audio", "flac"),
    MID("mid", "audio", "midi"),
    MIDI("midi", "audio", "midi"),
    KAR("kar", "audio", "midi"),
    MPGA("mpga", "audio", "mpeg"),
    MPEGA("mpega", "audio", "mpeg"),
    MP2("mp2", "audio", "mpeg"),
    MP3("mp3", "audio", "mpeg"),
    M4A("m4a", "audio", "mpeg"),
    M3U("m3u", "audio", "mpegurl"),
    OGA("oga", "audio", "ogg"),
    OGG("ogg", "audio", "ogg"),
    OPUS("opus", "audio", "ogg"),
    SPX("spx", "audio", "ogg"),
    SID("sid", "audio", "prs.sid"),
    AIF("aif", "audio", "x-aiff"),
    AIFF("aiff", "audio", "x-aiff"),
    AIFC("aifc", "audio", "x-aiff"),
    GSM("gsm", "audio", "x-gsm"),
    WMA("wma", "audio", "x-ms-wma"),
    WAX("wax", "audio", "x-ms-wax"),
    RA("ra", "audio", "x-pn-realaudio"),
    RM("rm", "audio", "x-pn-realaudio"),
    RAM("ram", "audio", "x-pn-realaudio"),
    PLS("pls", "audio", "x-scpls"),
    SD2("sd2", "audio", "x-sd2"),
    WAV("wav", "audio", "x-wav"),
    ALC("alc", "chemical", "x-alchemy"),
    CAC("cac", "chemical", "x-cache"),
    CACHE("cache", "chemical", "x-cache"),
    CSF("csf", "chemical", "x-cache-csf"),
    CBIN("cbin", "chemical", "x-cactvs-binary"),
    CASCII("cascii", "chemical", "x-cactvs-binary"),
    CTAB("ctab", "chemical", "x-cactvs-binary"),
    CDX("cdx", "chemical", "x-cdx"),
    CER("cer", "chemical", "x-cerius"),
    C3D("c3d", "chemical", "x-chem3d"),
    CHM("chm", "chemical", "x-chemdraw"),
    CIF("cif", "chemical", "x-cif"),
    CMDF("cmdf", "chemical", "x-cmdf"),
    CML("cml", "chemical", "x-cml"),
    CPA("cpa", "chemical", "x-compass"),
    BSD("bsd", "chemical", "x-crossfire"),
    CSML("csml", "chemical", "x-csml"),
    CSM("csm", "chemical", "x-csml"),
    CTX("ctx", "chemical", "x-ctx"),
    CXF("cxf", "chemical", "x-cxf"),
    CEF("cef", "chemical", "x-cxf"),
    EMB("emb", "chemical", "x-embl-dl-nucleotide"),
    EMBL("embl", "chemical", "x-embl-dl-nucleotide"),
    SPC("spc", "chemical", "x-galactic-spc"),
    INP("inp", "chemical", "x-gamess-input"),
    GAM("gam", "chemical", "x-gamess-input"),
    GAMIN("gamin", "chemical", "x-gamess-input"),
    FCH("fch", "chemical", "x-gaussian-checkpoint"),
    FCHK("fchk", "chemical", "x-gaussian-checkpoint"),
    CUB("cub", "chemical", "x-gaussian-cube"),
    GAU("gau", "chemical", "x-gaussian-input"),
    GJC("gjc", "chemical", "x-gaussian-input"),
    GJF("gjf", "chemical", "x-gaussian-input"),
    GAL("gal", "chemical", "x-gaussian-log"),
    GCG("gcg", "chemical", "x-gcg8-sequence"),
    GEN("gen", "chemical", "x-genbank"),
    HIN("hin", "chemical", "x-hin"),
    ISTR("istr", "chemical", "x-isostar"),
    IST("ist", "chemical", "x-isostar"),
    JDX("jdx", "chemical", "x-jcamp-dx"),
    DX("dx", "chemical", "x-jcamp-dx"),
    KIN("kin", "chemical", "x-kinemage"),
    MCM("mcm", "chemical", "x-macmolecule"),
    MMD("mmd", "chemical", "x-macromodel-input"),
    MMOD("mmod", "chemical", "x-macromodel-input"),
    MOL("mol", "chemical", "x-mdl-molfile"),
    RD("rd", "chemical", "x-mdl-rdfile"),
    RXN("rxn", "chemical", "x-mdl-rxnfile"),
    SD("sd", "chemical", "x-mdl-sdfile"),
    TGF("tgf", "chemical", "x-mdl-tgf"),
    MCIF("mcif", "chemical", "x-mmcif"),
    MOL2("mol2", "chemical", "x-mol2"),
    B("b", "chemical", "x-molconn-Z"),
    GPT("gpt", "chemical", "x-mopac-graph"),
    MOP("mop", "chemical", "x-mopac-input"),
    MOPCRT("mopcrt", "chemical", "x-mopac-input"),
    MPC("mpc", "chemical", "x-mopac-input"),
    ZMT("zmt", "chemical", "x-mopac-input"),
    MOO("moo", "chemical", "x-mopac-out"),
    MVB("mvb", "chemical", "x-mopac-vib"),
    ASN("asn", "chemical", "x-ncbi-asn1"),
    PRT("prt", "chemical", "x-ncbi-asn1-ascii"),
    ENT("ent", "chemical", "x-ncbi-asn1-ascii"),
    VAL("val", "chemical", "x-ncbi-asn1-binary"),
    ASO("aso", "chemical", "x-ncbi-asn1-binary"),
    PDB("pdb", "chemical", "x-pdb"),
    ROS("ros", "chemical", "x-rosdal"),
    SW("sw", "chemical", "x-swissprot"),
    VMS("vms", "chemical", "x-vamas-iso14976"),
    VMD("vmd", "chemical", "x-vmd"),
    XTEL("xtel", "chemical", "x-xtel"),
    XYZ("xyz", "chemical", "x-xyz"),
    GIF("gif", "image", "gif"),
    IEF("ief", "image", "ief"),
    JP2("jp2", "image", "jp2"),
    JPG2("jpg2", "image", "jp2"),
    JPEG("jpeg", "image", "jpeg"),
    JPG("jpg", "image", "jpeg"),
    JPE("jpe", "image", "jpeg"),
    JPM("jpm", "image", "jpm"),
    JPX("jpx", "image", "jpx"),
    JPF("jpf", "image", "jpx"),
    PCX("pcx", "image", "pcx"),
    PNG("png", "image", "png"),
    SVG("svg", "image", "svg+xml"),
    SVGZ("svgz", "image", "svg+xml"),
    TIFF("tiff", "image", "tiff"),
    TIF("tif", "image", "tiff"),
    DJVU("djvu", "image", "vnd.djvu"),
    DJV("djv", "image", "vnd.djvu"),
    ICO("ico", "image", "vnd.microsoft.icon"),
    WBMP("wbmp", "image", "vnd.wap.wbmp"),
    CR2("cr2", "image", "x-canon-cr2"),
    CRW("crw", "image", "x-canon-crw"),
    RAS("ras", "image", "x-cmu-raster"),
    CDR("cdr", "image", "x-coreldraw"),
    PAT("pat", "image", "x-coreldrawpattern"),
    CDT("cdt", "image", "x-coreldrawtemplate"),
    ERF("erf", "image", "x-epson-erf"),
    ART("art", "image", "x-jg"),
    JNG("jng", "image", "x-jng"),
    BMP("bmp", "image", "x-ms-bmp"),
    NEF("nef", "image", "x-nikon-nef"),
    ORF("orf", "image", "x-olympus-orf"),
    PSD("psd", "image", "x-photoshop"),
    PNM("pnm", "image", "x-portable-anymap"),
    PBM("pbm", "image", "x-portable-bitmap"),
    PGM("pgm", "image", "x-portable-graymap"),
    PPM("ppm", "image", "x-portable-pixmap"),
    RGB("rgb", "image", "x-rgb"),
    XBM("xbm", "image", "x-xbitmap"),
    XPM("xpm", "image", "x-xpixmap"),
    XWD("xwd", "image", "x-xwindowdump"),
    EML("eml", "message", "rfc822"),
    IGS("igs", "model", "iges"),
    IGES("iges", "model", "iges"),
    MSH("msh", "model", "mesh"),
    MESH("mesh", "model", "mesh"),
    SILO("silo", "model", "mesh"),
    WRL("wrl", "model", "vrml"),
    VRML("vrml", "model", "vrml"),
    X3DV("x3dv", "model", "x3d+vrml"),
    X3D("x3d", "model", "x3d+xml"),
    X3DB("x3db", "model", "x3d+binary"),
    APPCACHE("appcache", "text", "cache-manifest"),
    ICS("ics", "text", "calendar"),
    ICZ("icz", "text", "calendar"),
    CSS("css", "text", "css"),
    CSV("csv", "text", "csv"),
    _323("323", "text", "h323"),
    HTML("html", "text", "html"),
    HTM("htm", "text", "html"),
    SHTML("shtml", "text", "html"),
    ULS("uls", "text", "iuls"),
    MML("mml", "text", "mathml"),
    ASC("asc", "text", "plain"),
    TXT("txt", "text", "plain"),
    TEXT("text", "text", "plain"),
    POT("pot", "text", "plain"),
    BRF("brf", "text", "plain"),
    SRT("srt", "text", "plain"),
    RTX("rtx", "text", "richtext"),
    SCT("sct", "text", "scriptlet"),
    WSC("wsc", "text", "scriptlet"),
    TM("tm", "text", "texmacs"),
    TSV("tsv", "text", "tab-separated-values"),
    TTL("ttl", "text", "turtle"),
    JAD("jad", "text", "vnd.sun.j2me.app-descriptor"),
    WML("wml", "text", "vnd.wap.wml"),
    WMLS("wmls", "text", "vnd.wap.wmlscript"),
    BIB("bib", "text", "x-bibtex"),
    BOO("boo", "text", "x-boo"),
    H_P_P("h++", "text", "x-c++hdr"),
    HPP("hpp", "text", "x-c++hdr"),
    HXX("hxx", "text", "x-c++hdr"),
    HH("hh", "text", "x-c++hdr"),
    C_P_P("c++", "text", "x-c++src"),
    CPP("cpp", "text", "x-c++src"),
    CXX("cxx", "text", "x-c++src"),
    CC("cc", "text", "x-c++src"),
    H("h", "text", "x-chdr"),
    HTC("htc", "text", "x-component"),
    C("c", "text", "x-csrc"),
    D("d", "text", "x-dsrc"),
    DIFF("diff", "text", "x-diff"),
    PATCH("patch", "text", "x-diff"),
    HS("hs", "text", "x-haskell"),
    JAVA("java", "text", "x-java"),
    LY("ly", "text", "x-lilypond"),
    LHS("lhs", "text", "x-literate-haskell"),
    MOC("moc", "text", "x-moc"),
    P("p", "text", "x-pascal"),
    PAS("pas", "text", "x-pascal"),
    GCD("gcd", "text", "x-pcs-gcd"),
    PL("pl", "text", "x-perl"),
    PM("pm", "text", "x-perl"),
    PY("py", "text", "x-python"),
    SCALA("scala", "text", "x-scala"),
    ETX("etx", "text", "x-setext"),
    SFV("sfv", "text", "x-sfv"),
    TK("tk", "text", "x-tcl"),
    TEX("tex", "text", "x-tex"),
    LTX("ltx", "text", "x-tex"),
    STY("sty", "text", "x-tex"),
    CLS("cls", "text", "x-tex"),
    VCS("vcs", "text", "x-vcalendar"),
    VCF("vcf", "text", "x-vcard"),
    _3GP("3gp", "video", "3gpp"),
    AXV("axv", "video", "annodex"),
    DL("dl", "video", "dl"),
    DIF("dif", "video", "dv"),
    DV("dv", "video", "dv"),
    FLI("fli", "video", "fli"),
    GL("gl", "video", "gl"),
    MPEG("mpeg", "video", "mpeg"),
    MPG("mpg", "video", "mpeg"),
    MPE("mpe", "video", "mpeg"),
    TS("ts", "video", "MP2T"),
    MP4("mp4", "video", "mp4"),
    QT("qt", "video", "quicktime"),
    MOV("mov", "video", "quicktime"),
    OGV("ogv", "video", "ogg"),
    WEBM("webm", "video", "webm"),
    MXU("mxu", "video", "vnd.mpegurl"),
    FLV("flv", "video", "x-flv"),
    LSF("lsf", "video", "x-la-asf"),
    LSX("lsx", "video", "x-la-asf"),
    MNG("mng", "video", "x-mng"),
    ASF("asf", "video", "x-ms-asf"),
    ASX("asx", "video", "x-ms-asf"),
    WM("wm", "video", "x-ms-wm"),
    WMV("wmv", "video", "x-ms-wmv"),
    WMX("wmx", "video", "x-ms-wmx"),
    WVX("wvx", "video", "x-ms-wvx"),
    AVI("avi", "video", "x-msvideo"),
    MOVIE("movie", "video", "x-sgi-movie"),
    MPV("mpv", "video", "x-matroska"),
    MKV("mkv", "video", "x-matroska"),
    ICE("ice", "x-conference", "x-cooltalk"),
    SISX("sisx", "x-epoc", "x-sisx-app"),
    VRM("vrm", "x-world", "x-vrml")
    ;

    private String ext;
    private String type;
    private String subType;

    private MediaTypes(String ext, String type, String subType) {
        setExt(ext);
        setType(type);
        setSubType(subType);
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getContentType()
    {
        return type+"/"+subType;
    }

}
