package koDong.testSpringBoot.infrastructure;

public class NoEntityInProductList extends RuntimeException {

    public NoEntityInProductList(String message) {
        super(message);
    }

}
