package com.example.interchange.info;

/**
 * @author 秃头小刘
 * @date 2021-12-06 4:23 PM
 */


public class ChainConfigInfo {
//     public static String ChainFilePath = "/home/app/interchange";
    public static String ChainFilePath = "/Users/cuijingyi/interchange";
    public static String commandEnv = "/bin/sh";
//    public static String commandInMac = "/bin/sh";
//    public static String commandInLinux = "sh";

    public static String[][] ChainPortInfo = new String[][]{
            {"--chain-id CompanyA","--home ~/.CompanyA","","http://localhost:4500/"},
            {"--chain-id CompanyB","--home ~/.CompanyB","--node tcp://localhost:26659","http://localhost:4501/"},
            {"--chain-id CompanyC","--home ~/.CompanyC","--node tcp://localhost:26661","http://localhost:4502/"},
    };
}
