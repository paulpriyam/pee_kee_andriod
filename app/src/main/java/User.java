import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("userId")
    private int userId;
    //generalyy don't send integer because json don't receive integer value
    //if its int ,json will convert it to zero as its not nullable so convert it into Integer so json automatically take it as null
    //id is always created by jason
    public User(Integer id, String title, String body, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getBody(){
        return body;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    @Override
    public String toString(){
        return
                "User{" +
                        "id = '" + id + '\'' +
                        ",title = '" + title + '\'' +
                        ",body = '" + body + '\'' +
                        ",userId = '" + userId + '\'' +
                        "}";
    }
}
