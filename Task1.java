public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {
    //FIX: Initialize the ArrayList to avoid nullPointerException
    List<LoanAccount> result = new ArrayList<>();

    // FIX: Handle null input list
    if (accounts == null) {
        return result;
    }

    for (LoanAccount account : accounts) {
        //FIX: Check for due date as it can be null
        If(account.getDueDate() == null){
            continue;
        }
        if (account.getDueDate().before(new Date())) {
            if (account.getOutstandingBalance() > 0) {
                result.add(account);
            }
        }
    }
    return result;
}

// LoanAccount fields:
// Date dueDate          — may be null for restructured accounts
// double outstandingBalance
// String accountId      — always non-null
