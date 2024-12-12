package BackEnd;

public class MemberFactoryImpl implements MemberFactory {

    @Override
    public MemberShip createMember(String memberType) {
        switch (memberType) {
            case "Member":
                return new Member();
            case "NormalAdmin":
                return new NormalAdmin();
            case "PrimaryAdmin":
                return new PrimaryAdmin();
            default:
                throw new IllegalArgumentException("Invalid member type: " + memberType);
        }
    }
}