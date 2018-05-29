package test.Enum;

public enum CompanyEnum {
    SF("顺丰速运", 1001), YTO("圆通速递", 1002), STO("申通物流", 1003), YD("韵达快运", 1004), YZPY("中国邮政", 1005);
    private CompanyEnum(String company, int code) {
        this.company = company;
        this.code = code;
    }
    private String company; // 公司名称
    private int code; // 公司编码
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    //根据物流公司名字获取对应的编码
    public static int getCodeByCompany(String company) {
        for (CompanyEnum c : CompanyEnum.values()) {
            if (c.getCompany().equals(company.trim())) {
                return c.code;
            }
        }
        return 0;
    }
    //根据物流公司编码获取对应的名字
    public static String getCompanyByCode(int code) {
        for (CompanyEnum c : CompanyEnum.values()) {
            if (c.getCode() == code) {
                return c.getCompany();
            }
        }
        return null;
    }
}