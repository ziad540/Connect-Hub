package BackEnd;

public class ProfileInformation {
    private String ProfilePicPath;
    private String CoverPicPath;
    private String bioData;

    private ProfileInformation() {
    }

    public ProfileInformation(String profilePicPath, String coverPicPath, String bioData) {
        this.ProfilePicPath = profilePicPath;
        this.CoverPicPath = coverPicPath;
        this.bioData = bioData;
    }

    public String getCoverPicPath() {
        return CoverPicPath;
    }

    public String getProfilePicPath() {
        return ProfilePicPath;
    }

    public String getBioData() {
        return bioData;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.ProfilePicPath = profilePicPath;
    }

    public void setCoverPicPath(String coverPicPath) {
        this.CoverPicPath = coverPicPath;
    }

    public void setBioData(String bioData) {
        this.bioData = bioData;
    }
}
