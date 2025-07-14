package LLD.ConceptAndCoding.ZZZ_Examples.E_ATM.Code.States;

public enum TransactionType {
    CHECK_BALANCE(1), WITHDRAW_AMOUNT(2);

    int value;

    TransactionType(int value) {
        this.value=value;
    }

    public static TransactionType getTranscation(int value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid transaction value: " + value);
    }
}
