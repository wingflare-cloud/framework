package com.wingflare.lib.datascope;

import java.util.List;
import java.util.Map;

public class DPInfo {

    private Map<String, List<String>> whitelist;

    private Map<String, List<String>> blacklist;


    public Map<String, List<String>> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(Map<String, List<String>> whitelist) {
        this.whitelist = whitelist;
    }

    public Map<String, List<String>> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Map<String, List<String>> blacklist) {
        this.blacklist = blacklist;
    }
}
