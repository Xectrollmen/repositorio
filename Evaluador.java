import java.util.Map;

public class Evaluador {
    public static double evaluar(Nodo nodo, Map<String, Double> variables) {
        if (nodo.izquierdo == null && nodo.derecho == null) {
            if (variables.containsKey(nodo.valor)) {
                return variables.get(nodo.valor);
            } else {
                return Double.parseDouble(nodo.valor);
            }
        }

        double der = evaluar(nodo.derecho, variables);
        double izq = nodo.izquierdo != null ? evaluar(nodo.izquierdo, variables) : 0;

        return switch (nodo.valor) {
            case "+" -> izq + der;
            case "-" -> izq - der;
            case "*" -> izq * der;
            case "/" -> izq / der;
            case "^" -> Math.pow(izq, der);
            case "√" -> Math.sqrt(der);
            default -> 0;
        };
    }
}