package test.Enum;

public enum CompanyEnum {
    SF("˳������", 1001), YTO("Բͨ�ٵ�", 1002), STO("��ͨ����", 1003), YD("�ϴ����", 1004), YZPY("�й�����", 1005);
    private CompanyEnum(String company, int code) {
        this.company = company;
        this.code = code;
    }
    private String company; // ��˾����
    private int code; // ��˾����
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
    //����������˾���ֻ�ȡ��Ӧ�ı���
    public static int getCodeByCompany(String company) {
        for (CompanyEnum c : CompanyEnum.values()) {
            if (c.getCompany().equals(company.trim())) {
                return c.code;
            }
        }
        return 0;
    }
    //����������˾�����ȡ��Ӧ������
    public static String getCompanyByCode(int code) {
        for (CompanyEnum c : CompanyEnum.values()) {
            if (c.getCode() == code) {
                return c.getCompany();
            }
        }
        return null;
    }
}