package rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"postCode","ountry","countryAb"})
public class Helper {

    public static String postCode;
    public static String countryAb;
    public static String country;
    public Helper(){
         this.postCode="AD100";
         this.country ="Andorra";
         this.countryAb="AD";

    }
    @JsonProperty("post code")
    public  String getPostCode() {
        return postCode;
    }
    @JsonProperty("post code")
    public  void setPostCode(String postCode) {
        Helper.postCode = postCode;
    }
    @JsonProperty("country abbreviation")
    public  String getCountryAb() {
        return countryAb;
    }
    @JsonProperty("country abbreviation")
    public  void setCountryAb(String countryAb) {
        Helper.countryAb = countryAb;
    }
    @JsonProperty("country")
    public  String getCountry() {
        return country;
    }
    @JsonProperty("country")
    public  void setCountry(String country) {
        Helper.country = country;
    }
}
