import java.util.Scanner;

/**
 * Created by Rubén on 22/02/2017.
 */
public class Buscaminas {
    public static void main(String[] args) {
        int[][] tablero = new int[10][10];
        String[][] tablero2 = new String[8][8];
        Scanner teclado = new Scanner(System.in);
        int accion;
        int accionusar;
        int minas = 0;
        int resultado;
        //* While que pone las minas, en el tablero1
        while (minas < 10) {
            int i = (int) (Math.random() * 8)+1;
            int j = (int) (Math.random() * 8)+1;
            if (tablero[i][j] != 1) {
                tablero[i][j] = 1;
                minas++;
            }
        }
        //* For que cambia los valores del tablero2 por (/ Casilla sin descubrir)
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                tablero2[i][j] = "/";
            }
            System.out.println();
        }
        //* For que muestra el tablero2
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                System.out.print(tablero2[i][j] + " ");
            }
            System.out.println();
        }
        //* Mostrar tablero1 pruebas
        //for (int i = 0; i < tablero.length; i++) {
            //for (int j = 0; j < tablero[0].length; j++) {
                //System.out.print(tablero[i][j] + " ");
            //}
            //System.out.println();
        //}
        //* Bucle de menu y metodos a realizar
        accionusar = 1;
        while (accionusar == 1) {
            //* Acciones a Realizar
            System.out.println("Que acción quieres hacer:");
            System.out.println("Descubrir Celda = 1");
            System.out.println("Marcar Celda = 2");
            System.out.println("Desmarcar Celda = 3");
            accion = teclado.nextInt();
            if (accion == 1) {
                resultado=DescubrirCelda(tablero2, tablero);//* Va al metodo Descubrir
                if(resultado==1){
                    accionusar=0;
                    System.out.println("Has perdido");//*Condicion de perder
                }else if(resultado==2){
                    accionusar=0;
                    System.out.println("Has ganado");//*Condicion de perder
                }

            } else if (accion == 2) {
                MarcarCelda(tablero2);  //* Va al metodo Marcar

            } else if (accion == 3) {
                DesmarcarCelda(tablero2);  //* Va al metodo Desmarcar

            } else {
                System.out.println("Opción Incorrecta");
            }
        }
    }

    public static int DescubrirCelda(String[][] tablero2, int[][] tablero){
        int resultado=0;
        System.out.println("Descubrir");
        Scanner teclado = new Scanner(System.in);
        int contador=0;
        int contador2=0;
        String cadenacontador;
        System.out.println("Escribe en la fila que quieres recorrer: (Pon un valor del 0 al 7) ");//* Fila a recorrer
        int x= teclado.nextInt();
        System.out.println("Escribe la columna que quieres recorrer: (Pon un valor del 0 al 7)");//* Columna a recorrer
        int y= teclado.nextInt();
        if (tablero2[x][y]=="+"){
            System.out.println("Esta casilla esta marcada, desmarcala antes");//* No puedes descubrir casillas marcadas
        }else if(tablero[x+1][y+1]==1){
            tablero2[x][y]="*";
            System.out.println("Ha explotado una mina, perdiste");//* Saber que perdiste
            resultado=1;
        }else {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (tablero[i + 1][j + 1] == 1) {
                        contador++;//* Contador para los numeros de minas alrededor
                    }
                }
            }
            cadenacontador = String.valueOf(contador);
            tablero2[x][y] = cadenacontador; //* Mostrar cadena de numero de minas
        }
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                if(tablero2[i][j].equals("/")){
                    contador2++;
                }
                if(contador>=10){
                    resultado=2;
                }
            }
        }
        if(tablero2[x][y].equals("0")) {//* Descubrir las casillas de alrededor cuando te encuentras con una casilla que vale
            if (x> 0 && x<7 && y> 0  && y<7) {
                System.out.println("Hola me llamo ruben");
                // Celdas Central
                contador = 0;
                for (int i = x - 2; i <= x; i++) { // Arriba a la izquierda
                    for (int j = y - 2; j <= y; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x - 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 2; i <= x; i++) { //Arriba central
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x - 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 2; i <= x; i++) { //Arriba a la derecha
                    for (int j = y; j <= y + 2; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x - 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 1; i <= x + 1; i++) {  //Central a la izquierda
                    for (int j = y - 2; j <= y; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 1; i <= x + 1; i++) { //Central a la derecha
                    for (int j = y; j <= y + 2; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x; i <= x + 2; i++) { //Abajo a la izquierda
                    for (int j = y - 2; j <= y; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x + 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x; i <= x + 2; i++) { //Abajo central
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x + 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x; i <= x + 2; i++) { //Abajo a la derecha
                    for (int j = y; j <= y + 2; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x + 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
            }
            if (x == 0) { //Esquina arriba izquierda
                if (y == 0) {
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) { //Central a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x; i <= x + 2; i++) { //Abajo central
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x + 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x; i <= x + 2; i++) { //Abajo a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x + 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                } else if (y == 7) {
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) {  //Central a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x; i <= x + 2; i++) { //Abajo a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x + 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x; i <= x + 2; i++) { //Abajo central
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x + 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                } else {
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) {  //Central a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) { //Central a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x; i <= x + 2; i++) { //Abajo a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x + 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x; i <= x + 2; i++) { //Abajo central
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x + 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x; i <= x + 2; i++) { //Abajo a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x + 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                }
            }
            if (y == 0) {
                contador = 0;
                for (int i = x - 2; i <= x; i++) { //Arriba central
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x - 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 2; i <= x; i++) { //Arriba a la derecha
                    for (int j = y; j <= y + 2; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x - 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 1; i <= x + 1; i++) { //Central a la derecha
                    for (int j = y; j <= y + 2; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x; i <= x + 2; i++) { //Abajo central
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x + 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x; i <= x + 2; i++) { //Abajo a la derecha
                    for (int j = y; j <= y + 2; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x + 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
            }
            if (y == 7) {
                contador = 0;
                for (int i = x - 2; i <= x; i++) { // Arriba a la izquierda
                    for (int j = y - 2; j <= y; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x - 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 2; i <= x; i++) { //Arriba central
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x - 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x - 1; i <= x + 1; i++) {  //Central a la izquierda
                    for (int j = y - 2; j <= y; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x; i <= x + 2; i++) { //Abajo a la izquierda
                    for (int j = y - 2; j <= y; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x + 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                contador = 0;
                for (int i = x; i <= x + 2; i++) { //Abajo central
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (tablero[i + 1][j + 1] == 1) {
                            contador++;
                        }
                    }
                }
                cadenacontador = String.valueOf(contador);
                tablero2[x + 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
            }
            if (x == 7) {
                if (y == 0) {
                    contador = 0;
                    for (int i = x - 2; i <= x; i++) { //Arriba central
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x - 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 2; i <= x; i++) { //Arriba a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x - 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) { //Central a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                } else if (y == 7) {
                    contador = 0;
                    for (int i = x - 2; i <= x; i++) { // Arriba a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x - 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 2; i <= x; i++) { //Arriba central
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x - 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) {  //Central a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                } else {
                    contador = 0;
                    for (int i = x - 2; i <= x; i++) { // Arriba a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x - 1][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 2; i <= x; i++) { //Arriba central
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x - 1][y] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 2; i <= x; i++) { //Arriba a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x - 1][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) {  //Central a la izquierda
                        for (int j = y - 2; j <= y; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y - 1] = cadenacontador; //* Mostrar cadena de numero de minas
                    contador = 0;
                    for (int i = x - 1; i <= x + 1; i++) { //Central a la derecha
                        for (int j = y; j <= y + 2; j++) {
                            if (tablero[i + 1][j + 1] == 1) {
                                contador++;
                            }
                        }
                    }
                    cadenacontador = String.valueOf(contador);
                    tablero2[x][y + 1] = cadenacontador; //* Mostrar cadena de numero de minas
                }
            }
        }
        //* Volver a mostrar el tablero
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                System.out.print(tablero2[i][j] + " ");
            }
            System.out.println();
        }
        return resultado;
    }

    public static void MarcarCelda(String [][] tablero2){
        System.out.println("Marcar");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escribe en la fila que quieres recorrer: (Pon un valor del 0 al 7)");
        int x= teclado.nextInt();
        System.out.println("Escribe la columna que quieres recorrer: (Pon un valor del 0 al 7)");
        int y= teclado.nextInt();
        tablero2[x][y]="+";
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                System.out.print(tablero2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void DesmarcarCelda(String [][] tablero2){
        System.out.println("Desmarcar");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escribe en la fila que quieres recorrer: (Pon un valor del 0 al 7)");
        int x= teclado.nextInt();
        System.out.println("Escribe la columna que quieres recorrer: (Pon un valor del 0 al 7)");
        int y= teclado.nextInt();
        if (tablero2[x][y]=="+"){
            tablero2[x][y]="/";
        }else{
            System.out.println("Esta Casilla no esta marcada");
        }
        for (int i = 0; i < tablero2.length; i++) {
            for (int j = 0; j < tablero2[0].length; j++) {
                System.out.print(tablero2[i][j] + " ");
            }
            System.out.println();
        }
    }
}

