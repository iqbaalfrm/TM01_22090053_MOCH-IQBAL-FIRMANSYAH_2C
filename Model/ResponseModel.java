package Model;

public class ResponseModel {
    private String msg;
    private String status;
    private String comment;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ResponseModel(){

    }
    ResponseModel(String msg, String status, String comment){
        this.msg = msg;
        this.status = status;
        this.comment = comment;
    }

}
