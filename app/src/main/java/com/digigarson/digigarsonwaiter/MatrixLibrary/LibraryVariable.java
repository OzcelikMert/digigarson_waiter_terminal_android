package com.digigarson.digigarsonwaiter.MatrixLibrary;

public class LibraryVariable {

    /* Clear Variable */
    public Object ClearVar(Object Variable, String ClearType){
        Variable = (Variable != null) ? Variable : "";

        switch (ClearType){
            case "normal":
                Variable = Variable.toString().replaceAll(" ", "");
                Variable = Variable.toString().replaceAll("'", "");
                break;
            case "normal+number":
                Variable = Variable.toString().replaceAll(" ", "");
                Variable = Variable.toString().replaceAll("'", "");
                Variable = Variable.toString().replaceAll("([a-zA-Z])", "");
                Variable = (Variable.toString().equals("")) ? "0" : Variable;
                Variable = Integer.valueOf(Variable.toString());
                break;
        }

        return Variable;
    }
    /* end Clear Variable */
}
