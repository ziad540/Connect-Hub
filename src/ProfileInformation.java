public class ProfileInformation {
    private String ProfilePicPath;
    private String CoverPicPath;
    private String bioData;

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
        ProfilePicPath = profilePicPath;
    }

    public void setCoverPicPath(String coverPicPath) {
        CoverPicPath = coverPicPath;
    }

    public void setBioData(String bioData) {
        this.bioData = bioData;
    }
}
