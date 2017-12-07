package ufrn.campusseguro.Model;

public class RegistrosAssaltos {
    private int idLocal;
    private int id;
    private int idUsuario;
    private double lat;
    private double lng;
    private String data;
    private String descricao;

    public RegistrosAssaltos() {
    }

    public RegistrosAssaltos(int id, int idUsuario, double lat, double lng, String data, String descricao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.lat = lat;
        this.lng = lng;
        this.data = data;
        this.descricao = descricao;
    }

    public RegistrosAssaltos(int idLocal, int id, int idUsuario, double lat, double lng, String data, String descricao) {
        this.idLocal = idLocal;
        this.id = id;
        this.idUsuario = idUsuario;
        this.lat = lat;
        this.lng = lng;
        this.data = data;
        this.descricao = descricao;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

