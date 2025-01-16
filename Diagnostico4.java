public class Diagnostico4 {
    public static void main(String[] args) {
        int n_blancos = 4;
        int n_negros = 14;
        char puente[] = new char[n_blancos + n_negros + 1];
        int p;
        int fin = puente.length;
        boolean cambios = false;
        final int DERECHA = 1;
        final int IZQUIERDA = -1;
        int giro = 0;
        int izq, der;
        
        
        //INICIALIZAR EL PUENTE
        for (int i = 0; i < n_blancos; i++) {
            puente[i] = 'B';
        }
        
        puente[n_blancos] = '_';
        p = n_blancos;
        
        for (int i = n_blancos + 1; i < fin; i++) {
            puente[i] = 'N';
        }
        
        System.out.print("PUENTE INICIAL: \n");
        printArray(puente);
        
        //PRIMEROS DOS PASOS
        System.out.println("\nPRIMEROS DOS PASOS");
        if (n_blancos >= n_negros) {
            puente[p] = 'B';
            puente[p - 1] = '_';
            printArray(puente);
            puente[p - 1] = 'N';
            puente[p + 1] = '_';
            p++;
            giro = DERECHA;
            printArray(puente);
        } else {
            puente[p] = 'N';
            puente[p + 1] = '_';
            printArray(puente);
            puente[p - 1] = '_';
            puente[p + 1] = 'B';
            p--;
            giro = IZQUIERDA;
            printArray(puente);
        }
        
        System.out.println("\nINICIA ALGORITMO CICLICO");
        izq = -1;
        der = puente.length;
        do{
            cambios = false;
            while (puente[izq + 1] == 'N'){
                izq++;
            }
            
            while (puente [der - 1] == 'B') {
                der--;
            }
            
            if (izq + 3 == der) {//B_
                if (puente[izq + 1] == 'B' && puente[izq + 2] == '_') {//B_
                    System.out.print("OP 1 ");
                    puente[izq + 1] = '_';
                    puente[izq + 2] = 'B';
                    printArray(puente);
                    System.out.println("ALGORITMO FINALIZADO EXITOSAMENTE");
                    return;
                } else if (puente[izq + 1] == '_' && puente[izq + 2] == 'N') {//_N
                    System.out.print("OP 2 ");
                    puente[izq + 1] = 'N';
                    puente[izq + 2] = '_';
                    printArray(puente);
                    System.out.println("ALGORITMO FINALIZADO EXITOSAMENTE");
                    return;
                }
            } else if (izq + 1 == p) {//_bb //_bn   //_nb   //_nn
                if (puente[p+1] == 'B') {//-b
                    if (puente[p+2] == 'B') {
                        System.out.print("OP 3 ");
                        System.out.println("EL ALGORITMO HA FALLADO. CODIGO: 0");
                        return;
                    } else {//-bn  nb-
                        System.out.print("OP 4 ");
                        puente[p] = 'N';
                        puente[p + 2] = '_';
                        izq++;//COMPROBAR
                        p += 2;
                        giro = DERECHA;
                        cambios = true;
                        printArray(puente);
                    }
                } else {//-n
                    if (puente[p+2] == 'B') {//-nb     N-B
                        System.out.print("OP 5 ");
                        puente[p] = 'N';
                        puente[p + 1] = '_';
                        p ++;
                        izq++;//COMPROBAR
                        giro = DERECHA;
                        cambios = true;
                        printArray(puente);
                    } else {//-NN   NN-
                        System.out.print("OP 6 ");
                        puente[p] = 'N';
                        puente[p + 2] = '_';
                        p += 2;
                        izq += 2;//COMPROBAR
                        giro = DERECHA;
                        cambios = true;
                        printArray(puente);
                    }
                }
            } else if (izq + 2 == p) {//B_B     //B_N   //N_B Y N_N YA ESTAN CUBIERTOS
                if (puente[p-1] == 'B') {//B_
                    if (puente[p+1] == 'B') {//b-b
                        System.out.print("OP 7 ");
                        //System.out.println("EL ALGORITMO HA FALLADO. CODIGO: 1");
                        giro = DERECHA;//return;
                    } else {//B-N -BN   NB- N-B
                        System.out.print("OP8.1 ");
                        puente[p] = 'B';
                        puente[p - 1] = '_';
                        printArray(puente);
                        puente[p - 1] = 'N'; 
                        puente[p + 1] = '_';
                        System.out.print("OP8.2 ");
                        printArray(puente);
                        p ++;
                        izq++;//COMPROBAR
                        giro = DERECHA;
                        cambios = true;
                    }
                } else {//n-
                    if (puente[p+1] == 'B') {//n-b
                        System.out.print("OP 9 ");
                        //System.out.println("EL ALGORITMO HA FALLADO. CODIGO: 1");
                        izq++;
                        giro = DERECHA;
                        //return;
                    } else {//n-N nn-
                        System.out.print("OP 10 ");
                        puente[p] = 'N';
                        puente[p + 1] = '_';
                        printArray(puente);
                        p ++;
                        izq += 2;//COMPROBAR
                        giro = DERECHA;
                        cambios = true;
                        printArray(puente);
                    }
                }
            } else if (izq + 3 == p) {//BB-     //BN-        // CUBIERTOS //NB-   //NN-
                if (puente[p-1] == 'B') {//b-
                    if (puente[p-2] == 'B') {//BB- //b-b
                        if (p+1 < puente.length && puente[p+1] == 'N') {//BB-N     //B-BN      //BN-B
                            System.out.print("OP 11.1 ");
                            puente[p] = 'B'; 
                            puente[p - 1] = '_';
                            p --;
                            giro = DERECHA;
                            cambios = true;
                            printArray(puente);
                        } else {
                            System.out.print("OP 11.2 \n");
                            giro = DERECHA;
                        }
                        
                    } else {//nb- //n-b
                        System.out.print("OP 12 ");
                        puente[p] = '_'; 
                        puente[p + 1] = 'B';
                        p --;
                        izq++;
                        giro = DERECHA;
                        cambios = true;
                        printArray(puente);
                    }
                } else {//BN-   //-nb   //n-b
                    if (puente[p-2] == 'B') {//BN-
                        System.out.print("OP 13.1 ");
                        puente[p-2] = '_';
                        puente[p] = 'B';
                        printArray(puente);
                        puente[p-1] = '_';
                        puente[p-2] = 'N';
                        p --;
                        izq++;
                        giro = DERECHA;
                        cambios = true;
                        System.out.print("OP 13.2 ");
                        printArray(puente);
                    } else {//NN-
                        System.out.print("OP 14 ");
                        izq += 2;
                        giro = DERECHA;
                    }
                }
            }
            if (der - 1 == p) {//BB-     //BN-       //NB-   //NN-
                if (puente[p-2] == 'B') {
                    if (puente[p-1] == 'B') {//bb-
                        System.out.print("OP 15 ");
                        puente[p-2] = '_';
                        puente[p] = 'B';
                        der -= 2;
                        p -= 2;
                        giro = IZQUIERDA;
                        cambios = true;
                        printArray(puente);
                    } else {//bn-
                        System.out.print("OP 16 ");
                        puente[p-2] = '_';
                        puente[p] = 'B';
                        der --;
                        p -= 2;
                        giro = IZQUIERDA;
                        cambios = true;
                        printArray(puente);
                    }
                } else {
                    if (puente[p-1] == 'B') {//NB- N-B
                        System.out.print("OP 17 ");
                        puente[p-1] = '_';
                        puente[p] = 'B';
                        der--;
                        p--;
                        giro = IZQUIERDA;
                        cambios = true;
                        printArray(puente);
                    } else {//NN-
                        System.out.print("OP 18 ");
                        System.out.println("EL PROGRAMA HA FALLADO. CODIGO 2");
                        giro = IZQUIERDA;
                        return;
                    }
                }
            } else if (der - 2 == p) {//B_N     N_N     //B_B  N_B  YA ESTAN CUBIERTOS
                if (puente[p-1] == 'B') {
                    if (puente[p+1] == 'B') {//B-B          //-BB
                        System.out.print("OP 19 ");
                        puente[p] = 'B';
                        puente[p-1] = '_';
                        p--;
                        der -= 2;
                        giro = IZQUIERDA;
                        cambios = true;
                        printArray(puente);
                    } else {//B-N   //bn-   //-nb
                        System.out.print("OP 20 ");
                        puente[p] = 'N';
                        puente[p+1] = '_';
                        printArray(puente);
                        puente[p-1] = '_';
                        puente[p+1] = 'B';
                        System.out.print("OP 20.1 ");
                        printArray(puente);
                        p--;
                        der --;
                        giro = IZQUIERDA;
                        cambios = true;
                    }
                } else {
                    //NO DEBERIA ENTRAR AQUI
                    if (puente[p+1] == 'B') {//N-B
                        if (puente.length > 1) {
                            if (puente[p-2] == 'B') {//BN-B
                                System.out.print("OP 21 ");
                                puente[p-2] = '_';
                                puente[p] = 'B';
                                printArray(puente);
                                puente[p-2] = 'N';
                                puente[p-1] = '_';
                                p--;
                                der --;
                                giro = IZQUIERDA;
                                cambios = true;
                                printArray(puente);
                            }else {
                                System.out.print("OP 22 ");
                                giro = IZQUIERDA;
                            }
                        } else {//N-B
                            System.out.print("OP 23 ");
                            System.out.println("PROGRAMA FINALIZADO. CODIGO 2");
                            return;
                        }
                    } else {//N-N
                        System.out.print("OP 24 ");
                        puente[p] = 'N';
                        puente[p+1] = '_';
                        p++;
                        printArray(puente);
                        //System.out.println("EL PROGRAMA HA FALLADO. CODIGO 3");
                        giro = IZQUIERDA;
                        cambios =  true;
                        //return;
                    }
                }
            } else if (der - 3 == p) {//_bn     //_nn       //CUBIERTOS //_bb    //_nb   
                if (puente[p+1] == 'B') {
                    if (puente[p+2] == 'B') {//-BB
                        System.out.print("OP 25 ");
                        der -= 2;
                        giro = IZQUIERDA;
                    } else {//-BN   //NB-   //N-B
                        System.out.print("OP 26 ");
                        puente[p] = 'N';
                        puente[p+2] = '_';
                        printArray(puente);
                        puente[p+2] = 'B';
                        puente[p+1] = '_';
                        printArray(puente);
                        p++;
                        der--;
                        giro = IZQUIERDA;
                        cambios = true;
                    }
                } else {
                    if (puente[p+2] == 'B') {//-nb
                        System.out.print("OP 27 ");
                        der--;
                        giro = IZQUIERDA;
                    } else {//-nn   //n-n
                        //System.out.println("EL PROGRAMA HA FALLADO. CODIGO 4");
                        if (puente[p-1] == 'N') {
                            //NO SE PUEDE HACER NADA
                            System.out.print("OP 27.1 \n");
                        } else {
                            System.out.print("OP 28 ");
                            puente[p] = 'N';
                            puente[p + 1] = '_';
                            printArray(puente);
                            p++;
                            cambios = true;
                        }
                        giro = IZQUIERDA;
                    }
                }
            }
            
                        
            if (p == izq + 3 || p == der - 3) {
                if (puente[p-2] == 'B') {//B
                    if (puente[p-1] == 'B') {//BB-
                        if (puente[p+1] == 'B') {//BB-B
                            if (puente[p+2] == 'B') {//BB-BB/*System.out.println("CORRECCION DE GIRO: 0");*/
                            } else {//BB-BN //BBNB-   //BBN-B
                                System.out.println("CORRECCION DE GIRO: 1");
                                /*puente[p] = 'N';
                                puente[p + 2] = '_';
                                printArray(puente);
                                puente[p + 1] = '_';
                                puente[p + 2] = 'B';
                                printArray(puente);
                                
                                p++;
                                giro = IZQUIERDA;
                                cambios = true;*/
                            }
                        } else {//BB-N
                            if (puente[p+2] == 'B') {//BB-NB
                                System.out.println("CORRECCION DE GIRO: 2");
                                //giro = DERECHA;
                            } else {//BB-NN
                                System.out.println("CORRECCION DE GIRO: 3");
                                /*puente[p] = 'B';
                                puente[p - 1] = '_';
                                printArray(puente);
                                puente[p - 1] = 'N';
                                puente[p + 1] = '_';
                                p++;*/
                                //giro = DERECHA;
                                /*printArray(puente);
                                cambios = true;*/
                            }
                        }
                    } else {//BN-
                        if (puente[p+1] == 'B') {//BN-B
                            if (puente[p+2] == 'B') {//BN-BB
                                System.out.println("CORRECCION DE GIRO: 4");
                                //giro = DERECHA;
                                //giro = IZQUIERDA;
                            } else {//BN-BN
                                System.out.println("CORRECCION DE GIRO: 5");
                                //giro = IZQUIERDA;
                                //giro = DERECHA;
                            }
                        } else {//BN-N
                            if (puente[p+2] == 'B') {//BN-NB
                                System.out.println("CORRECCION DE GIRO: 6");
                                //giro = IZQUIERDA;
                                //giro = DERECHA;
                                //cambios = true;
                            } else {//BN-NN
                                System.out.println("CORRECCION DE GIRO: 7");
                                /*puente[p] = 'B';
                                puente[p-2] = '_';
                                p -= 2;
                                printArray(puente);
                                cambios = true;
                                //giro = IZQUIERDA;
                                //giro = DERECHA;
                                //cambios = true;*/
                            }
                        }
                    }
                } else {//N
                    if (puente[p-1] == 'B') {//NB-
                        if (puente[p+1] == 'B') {//NB-B
                            if (puente[p+2] == 'B') {//NB-BB
                                System.out.println("CORRECCION DE GIRO: 8");
                                //giro = IZQUIERDA;
                                //cambios = true;
                            } else {//NB-BN
                                System.out.println("CORRECCION DE GIRO: 9");
                                //giro = DERECHA;
                                //cambios = true;
                            }
                        } else {//NB-N
                            if (puente[p+2] == 'B') {//NB-NB
                                /*puente[p-1] = '_';
                                puente[p] = 'B';
                                printArray(puente);
                                cambios = true;
                                p--;
                                giro = DERECHA;
                                cambios = true;*/
                                System.out.println("CORRECCION DE GIRO: 10");
                            } else {//NB-NN
                                /*puente[p-1] = '_';
                                puente[p] = 'B';
                                printArray(puente);
                                cambios = true;
                                p--;
                                cambios = true;*/
                                //giro = DERECHA; 
                                //giro = IZQUIERDA;
                                System.out.println("CORRECCION DE GIRO: 11");
                            }
                        }
                    } else {//NN-
                        if (puente[p+1] == 'B') {//NN-B
                            if (puente[p+2] == 'B') {//NN-BB
                                //NO HACER NADA
                                System.out.println("CORRECCION DE GIRO: 12");
                            } else {//NN-BN
                                //giro = DERECHA;
                                //cambios = true
                                System.out.println("CORRECCION DE GIRO: 13");
                            }
                        } else {//NN-N
                            if (puente[p+2] == 'B') {//NN-NB
                                System.out.println("CORRECCION DE GIRO: 14");
                            } else {//NN-NN
                                //giro = DERECHA;
                                //cambios = true;
                                System.out.println("CORRECCION DE GIRO: 15");
                            }
                        }
                    }
                }
            }
            
            if (p > izq + 2 && p < der - 2){
               if (puente[p-2] == puente[p-1] && puente[p-1] == puente[p+1] && puente[p+1] == puente[p+2]) {
                  giro *= -1;
               }
            }

    
            if (giro == DERECHA) {
                while (p > izq + 1 && p < der - 1 && puente[p-1] == 'B' && puente[p+1] == 'N'){//B_N    //BN_
                    System.out.print("OP 29 ");
                    puente[p] = 'N';
                    puente[p+1] = '_';
                    p++;
                    cambios = true;
                    printArray(puente);
                    
                    if (p >= der - 3) {
                        System.out.print("OP 30 ");
                        giro = IZQUIERDA;
                        System.out.println("CORTE");
                        break;
                    }
                }//while (p > 1 && p < puente.length - 1 && puente[p-1] == 'B' && puente[p+1] == 'N'){
                
                while (p > izq && p < der - 2 && puente[p+1] == 'N' && puente[p+2] == 'B') {//_NB //N_B
                    System.out.print("OP 31 ");
                    puente[p+1] = '_';
                    puente[p] = 'N';
                    p++;
                    //giro = IZQUIERDA;
                    cambios = true;
                    printArray(puente);
                    if (p >= der - 3) {
                        System.out.print("OP 32 ");
                        giro = IZQUIERDA;
                        System.out.println("CORTE");
                        break;
                    }
                }
                
                while (p > izq && p < der - 2 && puente[p+1] == 'B' && puente[p+2] == 'N'){//_BN   //NB_ 
                    System.out.print("OP 33 ");
                    puente[p] = 'N';
                    puente[p+2] = '_';
                    printArray(puente);
                    p += 2;
                    cambios = true;
                    if (p >= der - 3) {
                        System.out.print("OP 34 ");
                        giro = IZQUIERDA;
                        System.out.println("CORTE");
                        break;
                    }
                }
                
                while (p > izq && p < der - 2 && puente[p+1] == 'N' && puente[p+2] == 'B'){//_NB   //N_B
                    System.out.print("OP 35 ");
                    puente[p] = 'N';
                    puente[p+1] = '_';
                    p++;
                    cambios = true;
                    printArray(puente);
                    if (p >= der - 3) {
                        System.out.print("OP 36 ");
                        giro = IZQUIERDA;
                        System.out.println("CORTE");
                        break;
                    }
                }
                
                while (p > izq && p < der - 2 && puente[p+1] == 'B' && puente[p+2] == 'N'){//_BN //NB_
                    System.out.print("OP 37 ");
                    puente[p] = 'N';
                    puente[p+2] = '_';
                    p += 2;
                    cambios = true;
                    printArray(puente);
                    if (p >= der - 3) {
                        giro = IZQUIERDA;
                        System.out.println("CORTE");
                        break;
                    }
                }
            } else if (giro == IZQUIERDA) {//giro == IZQUIERDA
                while (p < der && p > izq + 2 && puente[p-2] == 'B' && puente[p-1] == 'N') {//BN_N      //BN_   //_NB
                    System.out.print("OP 38 ");
                    puente[p-2] = '_';
                    puente[p] = 'B';
                    p -= 2;
                    cambios = true;
                    printArray(puente);
                    
                    if (p <= izq + 3) {
                        System.out.print("OP 39 ");
                        giro = DERECHA;
                        System.out.println("CORTE");
                        break;
                    }
                }
                
                while (p > izq + 1 && p < der - 1 && puente[p-1] == 'B' && puente[p+1] == 'N') {//B_N    //_BN
                    System.out.print("OP 40 ");
                    puente[p-1] = '_';
                    puente[p] = 'B';
                    p--;
                    cambios = true;
                    printArray(puente);
                    
                    if (p <= izq + 3) {
                        System.out.print("OP 41 ");
                        giro = DERECHA;
                        System.out.println("CORTE");
                        break;
                    }
                }
                
                while (p > izq + 2 && p < der && puente[p-1] == 'B' && puente[p-2] == 'N') {//NB_     //N_B
                    System.out.print("OP 42 ");
                    puente[p-1] = '_';
                    puente[p] = 'B';
                    p--;
                    cambios = true;
                    printArray(puente);
                    
                    if (p <= izq + 3) {
                        System.out.print("OP 43 ");
                        giro = DERECHA;
                        System.out.println("CORTE");
                        break;
                    }
                }
                
                while (p < der && p > izq + 2 && puente[p-2] == 'B' && puente[p-1] == 'N') {//BN_N//_NBN //BN_ //_NB
                    System.out.print("OP 44 ");
                    puente[p-2] = '_';
                    puente[p] = 'B';
                    p -= 2;
                    cambios = true;
                    printArray(puente);
                    
                    if (p <= izq + 3) {
                        System.out.print("OP 45 ");
                        giro = DERECHA;
                        System.out.println("CORTE");
                        break;
                    }
                }
            }//else giro == IZQUIERDA
            
            if (!cambios) {
                System.out.println("EL ALGORITMO HA FALLADO");
                return;
            }
        }while (!isSolved(puente, n_negros));//FINALIZA CUANDO ESTE RESUELTO
        
        
    }//MAIN
    
    private static void printArray(char c[]) {
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
        }
        System.out.println("");
    }
    
    private static boolean isSolved (char c[], int nN){
        if (c[nN] != '_') return false;
        else for (int i = 0; i < nN; i++) {
            if (c[i] != 'N') return false;
        }
        
        System.out.println("FIN DEL ALGORITMO");
        return true;
    }
}
