package RPNCalculator;

/**
 * Creates a RPN class
 *
 * @author Raena Rahimi Bafrani
 * @version Jan 28, 2022
 *
 */
public class RPN {
    //~ Fields ................................................................
    private Stack<Integer> stack;
    private int result;

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * Default constructor. Initialize fields to default values
     */
    public RPN()
    {
        stack = new Stack<Integer>();
        result = 0;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param s
     */
    public void push(String s) {
        //TODO
    }


    /**
     * Place a description of your method here.
     */
    public void add() {
        //TODO
    }


    /**
     * Place a description of your method here.
     */
    public void subtract() {
        //TODO
    }

    /**
     * Place a description of your method here.
     */
    public void multiply() {
        //TODO
    }


    /**
     * Place a description of your method here.
     */
    public void divide() {
        //TODO
    }

    /**
     * Place a description of your method here.
     */
    public int eval() {
        return result;

    }


    /**
     * Place a description of your method here.
     */
    public static void main(String[] args) {
        //TODO
    }

}
