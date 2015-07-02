/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * CountryCode.java
 *
 * Created on 17. April 2004, 00:56
 */

package org.softsmithy.lib.util;

import org.softsmithy.lib.text.Localizable;
import java.util.*;
/**
 * http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
 * @author  puce
 *
 * Needs java v1.5
 */
//abstract class CountryCode{
//abstract class CountryCode extends TypesafeEnum{}

public enum CountryCode implements Localizable{
    
    AFGHANISTAN                                   ("AF", "AFG", "004"),
    ALBANIA                                       ("AL", "ALB", "008"),
    ALGERIA                                       ("DZ", "DZA", "012"),
    AMERICAN_SAMOA                                ("AS", "ASM", "016"),
    ANDORRA                                       ("AD", "AND", "020"),
    ANGOLA                                        ("AO", "AGO", "024"),
    ANGUILLA                                      ("AI", "AIA", "660"),
    ANTARCTICA                                    ("AQ", "ATA", "010"),
    ANTIGUA_AND_BARBUDA                           ("AG", "ATG", "028"),
    ARGENTINA                                     ("AR", "ARG", "032"),
    ARMENIA                                       ("AM", "ARM", "051"),
    ARUBA                                         ("AW", "ABW", "533"),
    AUSTRALIA                                     ("AU", "AUS", "036"),
    AUSTRIA                                       ("AT", "AUT", "040"),
    AZERBAIJAN                                    ("AZ", "AZE", "031"),
    BAHAMAS                                       ("BS", "BHS", "044"),
    BAHRAIN                                       ("BH", "BHR", "048"),
    BANGLADESH                                    ("BD", "BGD", "050"),
    BARBADOS                                      ("BB", "BRB", "052"),
    BELARUS                                       ("BY", "BLR", "112"),
    BELGIUM                                       ("BE", "BEL", "056"),
    BELIZE                                        ("BZ", "BLZ", "084"),
    BENIN                                         ("BJ", "BEN", "204"),
    BERMUDA                                       ("BM", "BMU", "060"),
    BHUTAN                                        ("BT", "BTN", "064"),
    BOLIVIA                                       ("BO", "BOL", "068"),
    BOSNIA_AND_HERZEGOWINA                        ("BA", "BIH", "070"),
    BOTSWANA                                      ("BW", "BWA", "072"),
    BOUVET_ISLAND                                 ("BV", "BVT", "074"),
    BRAZIL                                        ("BR", "BRA", "076"),
    BRITISH_INDIAN_OCEAN_TERRITORY                ("IO", "IOT", "086"),
    BRUNEI_DARUSSALAM                             ("BN", "BRN", "096"),
    BULGARIA                                      ("BG", "BGR", "100"),
    BURKINA_FASO                                  ("BF", "BFA", "854"),
    BURUNDI                                       ("BI", "BDI", "108"),
    CAMBODIA                                      ("KH", "KHM", "116"),
    CAMEROON                                      ("CM", "CMR", "120"),
    CANADA                                        ("CA", "CAN", "124"),
    CAPE_VERDE                                    ("CV", "CPV", "132"),
    CAYMAN_ISLANDS                                ("KY", "CYM", "136"),
    CENTRAL_AFRICAN_REPUBLIC                      ("CF", "CAF", "140"),
    CHAD                                          ("TD", "TCD", "148"),
    CHILE                                         ("CL", "CHL", "152"),
    CHINA                                         ("CN", "CHN", "156"),
    CHRISTMAS_ISLAND                              ("CX", "CXR", "162"),
    COCOS__KEELING__ISLANDS                       ("CC", "CCK", "166"),
    COLOMBIA                                      ("CO", "COL", "170"),
    COMOROS                                       ("KM", "COM", "174"),
    CONGO__DEMOCRATIC_REPUBLIC_OF__WAS_ZAIRE_     ("CD", "COD", "180"),
    CONGO__PEOPLE_S_REPUBLIC_OF                   ("CG", "COG", "178"),
    COOK_ISLANDS                                  ("CK", "COK", "184"),
    COSTA_RICA                                    ("CR", "CRI", "188"),
    COTE_D_IVOIRE                                 ("CI", "CIV", "384"),
    CROATIA__LOCAL_NAME__HRVATSKA_                ("HR", "HRV", "191"),
    CUBA                                          ("CU", "CUB", "192"),
    CYPRUS                                        ("CY", "CYP", "196"),
    CZECH_REPUBLIC                                ("CZ", "CZE", "203"),
    DENMARK                                       ("DK", "DNK", "208"),
    DJIBOUTI                                      ("DJ", "DJI", "262"),
    DOMINICA                                      ("DM", "DMA", "212"),
    DOMINICAN_REPUBLIC                            ("DO", "DOM", "214"),
    EAST_TIMOR                                    ("TL", "TLS", "626"),
    ECUADOR                                       ("EC", "ECU", "218"),
    EGYPT                                         ("EG", "EGY", "818"),
    EL_SALVADOR                                   ("SV", "SLV", "222"),
    EQUATORIAL_GUINEA                             ("GQ", "GNQ", "226"),
    ERITREA                                       ("ER", "ERI", "232"),
    ESTONIA                                       ("EE", "EST", "233"),
    ETHIOPIA                                      ("ET", "ETH", "231"),
    FALKLAND_ISLANDS__MALVINAS_                   ("FK", "FLK", "238"),
    FAROE_ISLANDS                                 ("FO", "FRO", "234"),
    FIJI                                          ("FJ", "FJI", "242"),
    FINLAND                                       ("FI", "FIN", "246"),
    FRANCE                                        ("FR", "FRA", "250"),
    FRANCE__METROPOLITAN                          ("FX", "FXX", "249"),
    FRENCH_GUIANA                                 ("GF", "GUF", "254"),
    FRENCH_POLYNESIA                              ("PF", "PYF", "258"),
    FRENCH_SOUTHERN_TERRITORIES                   ("TF", "ATF", "260"),
    GABON                                         ("GA", "GAB", "266"),
    GAMBIA                                        ("GM", "GMB", "270"),
    GEORGIA                                       ("GE", "GEO", "268"),
    GERMANY                                       ("DE", "DEU", "276"),
    GHANA                                         ("GH", "GHA", "288"),
    GIBRALTAR                                     ("GI", "GIB", "292"),
    GREECE                                        ("GR", "GRC", "300"),
    GREENLAND                                     ("GL", "GRL", "304"),
    GRENADA                                       ("GD", "GRD", "308"),
    GUADELOUPE                                    ("GP", "GLP", "312"),
    GUAM                                          ("GU", "GUM", "316"),
    GUATEMALA                                     ("GT", "GTM", "320"),
    GUINEA                                        ("GN", "GIN", "324"),
    GUINEA_BISSAU                                 ("GW", "GNB", "624"),
    GUYANA                                        ("GY", "GUY", "328"),
    HAITI                                         ("HT", "HTI", "332"),
    HEARD_AND_MC_DONALD_ISLANDS                   ("HM", "HMD", "334"),
    HONDURAS                                      ("HN", "HND", "340"),
    HONG_KONG                                     ("HK", "HKG", "344"),
    HUNGARY                                       ("HU", "HUN", "348"),
    ICELAND                                       ("IS", "ISL", "352"),
    INDIA                                         ("IN", "IND", "356"),
    INDONESIA                                     ("ID", "IDN", "360"),
    IRAN__ISLAMIC_REPUBLIC_OF_                    ("IR", "IRN", "364"),
    IRAQ                                          ("IQ", "IRQ", "368"),
    IRELAND                                       ("IE", "IRL", "372"),
    ISRAEL                                        ("IL", "ISR", "376"),
    ITALY                                         ("IT", "ITA", "380"),
    JAMAICA                                       ("JM", "JAM", "388"),
    JAPAN                                         ("JP", "JPN", "392"),
    JORDAN                                        ("JO", "JOR", "400"),
    KAZAKHSTAN                                    ("KZ", "KAZ", "398"),
    KENYA                                         ("KE", "KEN", "404"),
    KIRIBATI                                      ("KI", "KIR", "296"),
    KOREA__DEMOCRATIC_PEOPLE_S_REPUBLIC_OF        ("KP", "PRK", "408"),
    KOREA__REPUBLIC_OF                            ("KR", "KOR", "410"),
    KUWAIT                                        ("KW", "KWT", "414"),
    KYRGYZSTAN                                    ("KG", "KGZ", "417"),
    LAO_PEOPLE_S_DEMOCRATIC_REPUBLIC              ("LA", "LAO", "418"),
    LATVIA                                        ("LV", "LVA", "428"),
    LEBANON                                       ("LB", "LBN", "422"),
    LESOTHO                                       ("LS", "LSO", "426"),
    LIBERIA                                       ("LR", "LBR", "430"),
    LIBYAN_ARAB_JAMAHIRIYA                        ("LY", "LBY", "434"),
    LIECHTENSTEIN                                 ("LI", "LIE", "438"),
    LITHUANIA                                     ("LT", "LTU", "440"),
    LUXEMBOURG                                    ("LU", "LUX", "442"),
    MACAU                                         ("MO", "MAC", "446"),
    MACEDONIA__THE_FORMER_YUGOSLAV_REPUBLIC_OF    ("MK", "MKD", "807"),
    MADAGASCAR                                    ("MG", "MDG", "450"),
    MALAWI                                        ("MW", "MWI", "454"),
    MALAYSIA                                      ("MY", "MYS", "458"),
    MALDIVES                                      ("MV", "MDV", "462"),
    MALI                                          ("ML", "MLI", "466"),
    MALTA                                         ("MT", "MLT", "470"),
    MARSHALL_ISLANDS                              ("MH", "MHL", "584"),
    MARTINIQUE                                    ("MQ", "MTQ", "474"),
    MAURITANIA                                    ("MR", "MRT", "478"),
    MAURITIUS                                     ("MU", "MUS", "480"),
    MAYOTTE                                       ("YT", "MYT", "175"),
    MEXICO                                        ("MX", "MEX", "484"),
    MICRONESIA__FEDERATED_STATES_OF               ("FM", "FSM", "583"),
    MOLDOVA__REPUBLIC_OF                          ("MD", "MDA", "498"),
    MONACO                                        ("MC", "MCO", "492"),
    MONGOLIA                                      ("MN", "MNG", "496"),
    MONTSERRAT                                    ("MS", "MSR", "500"),
    MOROCCO                                       ("MA", "MAR", "504"),
    MOZAMBIQUE                                    ("MZ", "MOZ", "508"),
    MYANMAR                                       ("MM", "MMR", "104"),
    NAMIBIA                                       ("NA", "NAM", "516"),
    NAURU                                         ("NR", "NRU", "520"),
    NEPAL                                         ("NP", "NPL", "524"),
    NETHERLANDS                                   ("NL", "NLD", "528"),
    NETHERLANDS_ANTILLES                          ("AN", "ANT", "530"),
    NEW_CALEDONIA                                 ("NC", "NCL", "540"),
    NEW_ZEALAND                                   ("NZ", "NZL", "554"),
    NICARAGUA                                     ("NI", "NIC", "558"),
    NIGER                                         ("NE", "NER", "562"),
    NIGERIA                                       ("NG", "NGA", "566"),
    NIUE                                          ("NU", "NIU", "570"),
    NORFOLK_ISLAND                                ("NF", "NFK", "574"),
    NORTHERN_MARIANA_ISLANDS                      ("MP", "MNP", "580"),
    NORWAY                                        ("NO", "NOR", "578"),
    OMAN                                          ("OM", "OMN", "512"),
    PAKISTAN                                      ("PK", "PAK", "586"),
    PALAU                                         ("PW", "PLW", "585"),
    PALESTINIAN_TERRITORY__OCCUPIED               ("PS", "PSE", "275"),
    PANAMA                                        ("PA", "PAN", "591"),
    PAPUA_NEW_GUINEA                              ("PG", "PNG", "598"),
    PARAGUAY                                      ("PY", "PRY", "600"),
    PERU                                          ("PE", "PER", "604"),
    PHILIPPINES                                   ("PH", "PHL", "608"),
    PITCAIRN                                      ("PN", "PCN", "612"),
    POLAND                                        ("PL", "POL", "616"),
    PORTUGAL                                      ("PT", "PRT", "620"),
    PUERTO_RICO                                   ("PR", "PRI", "630"),
    QATAR                                         ("QA", "QAT", "634"),
    REUNION                                       ("RE", "REU", "638"),
    ROMANIA                                       ("RO", "ROU", "642"),
    RUSSIAN_FEDERATION                            ("RU", "RUS", "643"),
    RWANDA                                        ("RW", "RWA", "646"),
    SAINT_KITTS_AND_NEVIS                         ("KN", "KNA", "659"),
    SAINT_LUCIA                                   ("LC", "LCA", "662"),
    SAINT_VINCENT_AND_THE_GRENADINES              ("VC", "VCT", "670"),
    SAMOA                                         ("WS", "WSM", "882"),
    SAN_MARINO                                    ("SM", "SMR", "674"),
    SAO_TOME_AND_PRINCIPE                         ("ST", "STP", "678"),
    SAUDI_ARABIA                                  ("SA", "SAU", "682"),
    SENEGAL                                       ("SN", "SEN", "686"),
    SEYCHELLES                                    ("SC", "SYC", "690"),
    SIERRA_LEONE                                  ("SL", "SLE", "694"),
    SINGAPORE                                     ("SG", "SGP", "702"),
    SLOVAKIA__SLOVAK_REPUBLIC_                    ("SK", "SVK", "703"),
    SLOVENIA                                      ("SI", "SVN", "705"),
    SOLOMON_ISLANDS                               ("SB", "SLB", "090"),
    SOMALIA                                       ("SO", "SOM", "706"),
    SOUTH_AFRICA                                  ("ZA", "ZAF", "710"),
    SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS  ("GS", "SGS", "239"),
    SPAIN                                         ("ES", "ESP", "724"),
    SRI_LANKA                                     ("LK", "LKA", "144"),
    ST_HELENA                                     ("SH", "SHN", "654"),
    ST_PIERRE_AND_MIQUELON                        ("PM", "SPM", "666"),
    SUDAN                                         ("SD", "SDN", "736"),
    SURINAME                                      ("SR", "SUR", "740"),
    SVALBARD_AND_JAN_MAYEN_ISLANDS                ("SJ", "SJM", "744"),
    SWAZILAND                                     ("SZ", "SWZ", "748"),
    SWEDEN                                        ("SE", "SWE", "752"),
    SWITZERLAND                                   ("CH", "CHE", "756"),
    SYRIAN_ARAB_REPUBLIC                          ("SY", "SYR", "760"),
    TAIWAN                                        ("TW", "TWN", "158"),
    TAJIKISTAN                                    ("TJ", "TJK", "762"),
    TANZANIA__UNITED_REPUBLIC_OF                  ("TZ", "TZA", "834"),
    THAILAND                                      ("TH", "THA", "764"),
    TOGO                                          ("TG", "TGO", "768"),
    TOKELAU                                       ("TK", "TKL", "772"),
    TONGA                                         ("TO", "TON", "776"),
    TRINIDAD_AND_TOBAGO                           ("TT", "TTO", "780"),
    TUNISIA                                       ("TN", "TUN", "788"),
    TURKEY                                        ("TR", "TUR", "792"),
    TURKMENISTAN                                  ("TM", "TKM", "795"),
    TURKS_AND_CAICOS_ISLANDS                      ("TC", "TCA", "796"),
    TUVALU                                        ("TV", "TUV", "798"),
    UGANDA                                        ("UG", "UGA", "800"),
    UKRAINE                                       ("UA", "UKR", "804"),
    UNITED_ARAB_EMIRATES                          ("AE", "ARE", "784"),
    UNITED_KINGDOM                                ("GB", "GBR", "826"),
    UNITED_STATES                                 ("US", "USA", "840"),
    UNITED_STATES_MINOR_OUTLYING_ISLANDS          ("UM", "UMI", "581"),
    URUGUAY                                       ("UY", "URY", "858"),
    UZBEKISTAN                                    ("UZ", "UZB", "860"),
    VANUATU                                       ("VU", "VUT", "548"),
    VATICAN_CITY_STATE__HOLY_SEE_                 ("VA", "VAT", "336"),
    VENEZUELA                                     ("VE", "VEN", "862"),
    VIET_NAM                                      ("VN", "VNM", "704"),
    VIRGIN_ISLANDS__BRITISH_                      ("VG", "VGB", "092"),
    VIRGIN_ISLANDS__US_                           ("VI", "VIR", "850"),
    WALLIS_AND_FUTUNA_ISLANDS                     ("WF", "WLF", "876"),
    WESTERN_SAHARA                                ("EH", "ESH", "732"),
    YEMEN                                         ("YE", "YEM", "887"),
    YUGOSLAVIA                                    ("YU", "YUG", "891"),
    ZAMBIA                                        ("ZM", "ZMB", "894"),
    ZIMBABWE                                      ("ZW", "ZWE", "716");
    
    private static final Map<String, CountryCode> a2ToCode = new HashMap<String, CountryCode>();
    private static final Map<String, CountryCode> a3ToCode = new HashMap<String, CountryCode>();
    private static final Map<String, CountryCode> numberToCode = new HashMap<String, CountryCode>();
    
    static{
        for (CountryCode cc : values()){
            a2ToCode.put(cc.getA2(), cc);
            a3ToCode.put(cc.getA3(), cc);
            numberToCode.put(cc.getNumber(), cc);
        }
    }
    
    private final String a2;
    private final String a3;
    private final String number;
    /** Creates a new instance of CountryCode */
    private CountryCode(String a2, String a3, String number) {
        this.a2 = a2;
        this.a3 = a3;
        this.number = number;
    }
    
    public String getA2(){
        return a2;
    }
    public String getA3(){
        return a3;
    }
    public String getNumber(){
        return number;
    }
    
    @Override
    public String toString(){
        return getA2();
    }
    
    public static CountryCode getDefault(){
        return getCountryCode(Locale.getDefault());
    }
    
    public static CountryCode getCountryCode(Locale locale){
        return a2ToCode(locale.getCountry());
    }
    
    public static CountryCode a2ToCode(String a2){
        return a2ToCode.get(a2);
    }
    
    public static CountryCode a3ToCode(String a3){
        return a3ToCode.get(a3);
    }
    
    public static CountryCode numberToCode(String number){
        return numberToCode.get(number);
    }
    
    //public abstract String toString(LanguageCode inLanguageCode);
    
    @Override
    public String getDisplayString(Locale inLocale){
        //return Locales.getLocale(null, this).getDisplayCountry(inLocale); //TODO: Check: is this correct?
        return new Locale("", getA2()).getDisplayCountry(inLocale);
    }
    
    
    
    
}
