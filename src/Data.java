public class Data extends DataArray {
    private String name,affiliation,nationality,genKnow,url,gender,career,nickName;
    public Data(String nameD,String affiT,String nationA ,String genK,String urL ,String gend, String job,String nick){
        this.name = nameD;
        this.affiliation = affiT;
        this.nationality = nationA;
        this.gender = gend;
        this.genKnow = genK;
        this.url = urL;
        this.career = job;
        this.nickName = nick;
    }
    public void setData(String nameD,String affiT,String nationA ,String genK,String urL ,String gend, String job,String nick){
        this.name = nameD;
        this.affiliation = affiT;
        this.nationality = nationA;
        this.gender = gend;
        this.genKnow = genK;
        this.url = urL;
        this.career = job;
        this.nickName = nick;
    }
    public Data getData(){return this;}

    public String getName(){return name;}
    public String getAffiliation(){return affiliation;}
    public String getNationality(){return nationality;}
    public String getGenKnow(){return genKnow;}
    public String getUrl(){return url;}
    public String getGender(){return gender;}
    public String getCareer(){return career;}
    public String getNickName(){return nickName;}
}
