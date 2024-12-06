package BackEnd;

public class checkValid {
    private Validation validation;
    public checkValid(Validation validation) {
        this.validation = validation;
    }
    public Boolean test(String input) {
       return validation.checker(input);
    }
}
