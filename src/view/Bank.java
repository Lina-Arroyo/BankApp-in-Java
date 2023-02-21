package view;

import exception.BankException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import model.Persona;
import model.Cuenta;

public class Bank {

    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);
    ArrayList<Cuenta> listCuenta = new ArrayList<>();
    ArrayList<Persona> listPersona = new ArrayList<>();
    private int posicionArreglo = 0;
    private int opcion = 0;
    private int i = 0;
    private boolean ct = true;

    public void menuInit() {
        do {
            try {
                do {
                    System.out.print("MENU \n"
                            + "1. REGISTRAR PERSONA \n"
                            + "2. CONSULTAR PERSONA POR NOMBRE \n"
                            + "3. CONSULTAR PERSONA POR APELLIDO \n"
                            + "4. CONSULTAR PERSONA POR IDENTIFICACION \n"
                            + "5. CONSULTAR TODOS LOS REGISTROS \n"
                            + "6. ACTUALIZAR DATOS \n"
                            + "7. ASOCIAR CUENTA PERSONA \n"
                            + "8. REALIZAR ABONO CUENTA\n"
                            + "9. REALIZAR RETIRO CUENTA \n"
                            + "10.CAMBIAR ESTADO DE CUENTA\n"
                            + "11.CONSULTAR CUENTAS PERSONA\n"
                            + "12.SALIR DEL SISTEMA \n");
                    opcion = Integer.parseInt(br.readLine());
                    switch (opcion) {
                        case 1:
                            listPersona.add(this.formCreatePerson());
                            break;
                        case 2:
                            this.findByName();
                            break;
                        case 3:
                            this.findByLastName();
                            break;
                        case 4:
                            this.findById();
                            break;
                        case 5:
                            this.displayAllRecord();
                            break;
                        case 6:
                            System.out.println("PARA ACTUALIZAR NÚMERO DE TELEFONO INGRESE 1 - 2 PARA ACTUALIZAR EMAIL - 3 DIRECCION RESIDENCIA");
                            int opcionActualizar = Integer.parseInt(br.readLine());
                            switch (opcionActualizar) {
                                case 1:
                                    this.findByPhoneNumber();
                                    break;
                                case 2:
                                    this.findByEmail();
                                    break;
                                case 3:
                                    this.findByAddress();
                                    break;
                                default:
                                    System.out.println("OPCIÓN NO VALIDA");
                                    break;
                            }
                            break;
                        case 7:
                            listCuenta.add(this.asociateBankAccount());
                            break;
                        case 8:
                            this.payBankAccount();
                            break;
                        case 9:
                            this.withdrawBankAccount();
                            break;
                        case 10:
                            this.changeStateAccount();
                            break;
                        case 11:
                            this.accountsOfPerson();
                            break;
                        case 12:
                            System.out.println("GRACIAS POR UTILIZAR NUESTRO SISTEMA");
                            System.exit(opcion);
                            break;
                        default:
                            System.out.println("OPCIÓN NO VÁLIDA");
                            break;
                    }

                } while (opcion != 6);
            } catch (NumberFormatException ex) {
                System.out.println("ERROR: LAS OPCIONES VALIDAS SON VALORES NUMERICOS");
            } catch (NegativeArraySizeException ex) {
                System.out.println("ERROR: DEBEN SER VALORES MAYORES O IGUAL A 1");
            } catch (IOException io) {
                System.out.println("");
            } catch (BankException ex) {
                System.out.println("" + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("" + ex.getMessage());
            }
        } while (ct);
    }

    public Persona formCreatePerson() throws IOException, Exception {
        Persona person = new Persona();
        try {
            System.out.println("INGRESE El NOMBRE:");
            person.setName(br.readLine().toUpperCase());
            if (!this.validateOnlyCharacter(person.getName())) {
                throw new Exception("ERROR: EL NOMBRE SOLO DEBE CONTENER CARACTERES");
            }
            System.out.println("INGRESE APELLIDOS:");
            person.setLastName(br.readLine().toUpperCase());
            if (!this.validateOnlyCharacter(person.getLastName())) {
                throw new Exception("ERROR: EL APELLIDO SOLO DEBE CONTENER CARACTERES");
            }
            System.out.println("INGRESE IDENTIFICACION:");
            person.setIdNumber(Integer.parseInt(br.readLine()));
            System.out.println("INGRESE FECHA DE NACIMIENTO:");
            person.setBirthDay(br.readLine());
            System.out.println("INGRESE DIRECCION DE RESIDENCIA:");
            person.setAddress(br.readLine().toUpperCase());
            System.out.println("INGRESE DIRECCION DE CORREO ELECTRONICO:");
            person.setEmail(br.readLine().toLowerCase());
            System.out.println("INGRESE NUMERO DE TELEFONO:");
            person.setPhoneNumber(Integer.parseInt(br.readLine()));
            System.out.println("EL REGISTRO HA SIDO EXITOSO");
            this.formQueryPerson(person);
        } catch (IOException ex) {
            System.out.println("");
        }
        return person;
    }

    public void formQueryPerson(Persona personQuery) {
        System.out.println("LOS DATOS DE LA PERSONA ALMACENADA SON:");
        System.out.println("NOMBRES: " + personQuery.getName());
        System.out.println("APELLIDOS: " + personQuery.getLastName());
        System.out.println("NÚMERO DE IDENTIFIACION: " + personQuery.getIdNumber());
        System.out.println("FECHA DE NACIMIENTO: " + personQuery.getBirthDay());
        System.out.println("DIRECCIÓN DE RESIDENCIA: " + personQuery.getAddress());
        System.out.println("DIRECCIÓN DE CORREO ELECTRONICO: " + personQuery.getEmail());
        System.out.println("NUMERO DE TELEFONO: " + personQuery.getPhoneNumber());
    }

    public void displayAllRecord() {
        System.out.println("*******************************************");
        System.out.println("LOS DATOS REGISTRADOS HASTA EL MOMENTO SON:");
        for (int i = 0; i < listPersona.size(); i++) {
            this.formQueryPerson(listPersona.get(i));
        }
    }

    public void findById() throws IOException {
        try {
            System.out.println("INGRESE EL NÚMERO DE IDENTIFICACIÓN A CONSULTAR:");
            int idFind = Integer.parseInt(br.readLine());
            for (int j = 0; j < listPersona.size(); j++) {
                if (idFind == listPersona.get(j).getIdNumber()) {
                    System.out.println("¡PERSONA ENCONTRADA!");
                    this.formQueryPerson(listPersona.get(j));
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("*ERROR*");
            System.out.println("INGRESE SOLO VALORES NUMERICOS");
        }
    }

    public void findByName() throws IOException, Exception {
        System.out.println("INGRESE EL NOMBRE DE LA PERSONA A CONSULTAR:");
        String nameFind = br.readLine().toUpperCase();
        for (int j = 0; j < listPersona.size(); j++) {
            if (nameFind.trim().equals(listPersona.get(j).getName())) {
                System.out.println("¡PERSONA ENCONTRADA!");
                this.formQueryPerson(listPersona.get(j));

            }
        }
        if (!this.validateOnlyCharacter(nameFind)) {
            throw new Exception("ERROR: EL NOMBRE SOLO DEBE CONTENER CARACTERES");
        }
    }

    public void findByLastName() throws IOException, Exception {
        System.out.println("INGRESE EL APELLIDO DE LA PERSONA A CONSULTAR:");
        String lastNameFind = br.readLine().toUpperCase();
        for (int j = 0; j < listPersona.size(); j++) {
            if (lastNameFind.trim().equals(listPersona.get(j).getLastName())) {
                System.out.println("¡PERSONA ENCONTRADA!");
                this.formQueryPerson(listPersona.get(j));
            }
        }
        if (!this.validateOnlyCharacter(lastNameFind)) {
            throw new Exception("ERROR: EL APELLIDO SOLO DEBE CONTENER CARACTERES");
        }
    }

    public void findByEmail() throws IOException {
        System.out.println("INGRESE EL EMAIL DE LA PERSONA A CONSULTAR:");
        String email = br.readLine().toLowerCase();
        for (int j = 0; j < listPersona.size(); j++) {
            if (email.trim().equals(listPersona.get(j).getEmail())) {
                System.out.println("¡PERSONA ENCONTRADA!");
                this.formQueryPerson(listPersona.get(j));
                System.out.println("INGRESE EL NUEVO EMAIL");
                listPersona.get(j).setEmail(br.readLine().toLowerCase());
                this.formQueryPerson(listPersona.get(j));
            }
        }
    }

    public void findByAddress() throws IOException {
        System.out.println("INGRESE LA DIRECCION DE LA PERSONA A CONSULTAR:");
        String address = br.readLine().toLowerCase();
        for (int j = 0; j < listPersona.size(); j++) {
            if (address.trim().equals(listPersona.get(j).getAddress())) {
                System.out.println("¡PERSONA ENCONTRADA!");
                this.formQueryPerson(listPersona.get(j));
                System.out.println("INGRESE LA NUEVA DIRECCIÓN");
                listPersona.get(j).setAddress(br.readLine().toLowerCase());
                this.formQueryPerson(listPersona.get(j));
            }
        }
    }

    public void findByPhoneNumber() throws IOException {
        try {
            System.out.println("INGRESE EL NUMERO DE LA PERSONA A DE LA PERSONA A CONSULTAR:");
            int phoneNumber = Integer.parseInt(br.readLine());
            for (int j = 0; j < listPersona.size(); j++) {
                if (phoneNumber == listPersona.get(j).getPhoneNumber()) {
                    System.out.println("¡PERSONA ENCONTRADA!");
                    this.formQueryPerson(listPersona.get(j));
                    System.out.println("INGRESE EL NUEVO NÚMERO");
                    listPersona.get(j).setPhoneNumber(Integer.parseInt(br.readLine()));
                    this.formQueryPerson(listPersona.get(j));
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("*ERROR*");
            System.out.println("INGRESE SOLO VALORES NUMERICOS");
        }
    }

    //************************************************************************************************************//
    public Cuenta asociateBankAccount() throws IOException, Exception {
        Cuenta cuenta = new Cuenta();
        Persona person = new Persona();
        try {
            System.out.println("INGRESE EL NUMERO DE IDENTIFICACION DEL TITULAR DE LA CUENTA");
            int idFind = Integer.parseInt(br.readLine());
            for (int j = 0; j < listPersona.size(); j++) {
                if (idFind != listPersona.get(j).getIdNumber()) {
                    System.out.println("ERROR! EL TITULAR NO EXISTE O EL NUMERO DE IDENTIFICACION ES ERRADO");
                } else {
                    System.out.println("INGRESE EL TIPO DE CUENTA");
                    cuenta.setAccountType(br.readLine().toUpperCase().trim());
                    System.out.println("INGRESE EL SALDO DE LA CUENTA");
                    cuenta.setBalance(Integer.parseInt(br.readLine()));
                    System.out.println("INGRESE EL NUMERO DE CUENTA");
                    cuenta.setAccountNumber(Integer.parseInt(br.readLine()));
                    System.out.println("INGRESE EL ESTADO DE LA CUENTA");
                    cuenta.setAccountStatus((br.readLine().toUpperCase().trim()));
                    if (!this.validateOnlyCharacter(cuenta.getAccountStatus())) {
                        throw new Exception("ERROR: SOLO DEBE ESCRIBIR CARACTERES");
                    }
                    System.out.println("LA ASOCIACION HA SIDO EXITOSA");
                    this.formQueryAccount(cuenta);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("*ERROR*");
            System.out.println("INGRESE SOLO VALORES NUMERICOS");
        }
        return cuenta;
    }

    public void payBankAccount() throws IOException {
        try {
            System.out.println("INGRESE EL NÚMERO DE CUENTA EN LA QUE SE REALIZARA EL ABONO");
            int cuenta = Integer.parseInt(br.readLine());
            for (int j = 0; j < listPersona.size(); j++) {
                if (cuenta != listCuenta.get(j).getAccountNumber()) {
                    System.out.println("ERROR! LA CUENTA NO EXISTE O EL NUMERO DE CUENTA ES ERRADO");
                } else {
                    System.out.println("INGRESE EL VALOR A ABONAR");
                    int abono = Integer.parseInt(br.readLine());
                    if (abono > 0) {
                        System.out.println("ABONO EXITOSO!");
                        int newBalance = (int) (listCuenta.get(j).getBalance() + abono);
                        listCuenta.get(j).setBalance(newBalance);
                        System.out.println("EL SALDO ACTUAL DE SU CUENTA ES: " + newBalance);
                    } else {
                        System.out.println("ERROR! EL ABONO DEBE SER MAYOR A 0$");
                    }
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("*ERROR*");
            System.out.println("INGRESE SOLO VALORES NUMERICOS");
        }
    }

    public void withdrawBankAccount() throws IOException {
        try {
            System.out.println("INGRESE EL NÚMERO DE CUENTA EN LA QUE SE REALIZARA EL RETIRO");
            int cuenta2 = Integer.parseInt(br.readLine());
            for (int j = 0; j < listCuenta.size(); j++) {
                if (cuenta2 != listCuenta.get(j).getAccountNumber()) {
                    System.out.println("ERROR! LA CUENTA NO EXISTE O EL NUMERO DE CUENTA ES ERRADO");
                } else {
                    System.out.println("INGRESE EL VALOR A RETIRAR");
                    int retiro = Integer.parseInt(br.readLine());
                    if (listCuenta.get(j).getBalance() < 0) {
                        System.out.println("ERROR! FONDOS INSUFICIENTES");
                    } else {
                        System.out.println("RETIRO EXITOSO");
                        int newBalance = (int) (listCuenta.get(j).getBalance() - retiro);
                        listCuenta.get(j).setBalance(newBalance);
                        System.out.println("EL SALDO ACTUAL DE SU CUENTA ES: " + newBalance);
                    }
                }
            }

        } catch (NumberFormatException ex) {
            System.out.println("*ERROR*");
            System.out.println("INGRESE SOLO VALORES NUMERICOS");
        }
    }

    public void changeStateAccount() throws IOException, Exception {
        Cuenta cuenta = new Cuenta();
        try {
            System.out.println("INGRESE EL NUMERO DE CUENTA");
            int cuenta3 = Integer.parseInt(br.readLine());
            for (int j = 0; j < listCuenta.size(); j++) {
                if (cuenta3 != listCuenta.get(j).getAccountNumber()) {
                    System.out.println("ERROR! LA CUENTA NO EXISTE O EL NUMERO DE CUENTA ES ERRADO");
                } 
                else {
                    String status = "INACTIVA";
                    if (status == null ? listCuenta.get(j).getAccountStatus() == null : status.equals(listCuenta.get(j).getAccountStatus())){
                            System.out.println("ESCRIBA 'ACTIVA' PARA ACTIVAR LA CUENTA");
                            listCuenta.get(j).setAccountStatus((br.readLine().toUpperCase().trim()));
                            System.out.println("ACTIVACION DE CUENTA EXITOSO!");
                            this.formQueryAccount(listCuenta.get(j));
                    }
                    else{
                        if (listCuenta.get(j).getBalance() == 0){
                        System.out.println("ESCRIBA 'INACTIVA' PARA INACTIVAR LA CUENTA");
                        listCuenta.get(j).setAccountStatus((br.readLine().toUpperCase().trim()));
                        System.out.println("INACTIVACION DE CUENTA EXITOSO!");
                        this.formQueryAccount(listCuenta.get(j));
                        }
                        else{
                            System.out.println("ERROR! LOS FONDOS DE LA CUENTA NO DEBEN SER SUPERIORES A 0$");
                        }
                    }
                } 
            }
        } catch (NumberFormatException ex) {
            System.out.println("*ERROR*");
            System.out.println("INGRESE SOLO VALORES NUMERICOS");
        }
    }

    public void accountsOfPerson() throws IOException {
        Cuenta cuenta = new Cuenta();
        Persona person = new Persona();
        try {
            System.out.println("INGRESE EL NUMERO DE IDENTIFICACION DEL TITULAR DE LA CUENTA");
            int idFind = Integer.parseInt(br.readLine());
            for (int j = 0; j < listPersona.size(); j++) {
                if (idFind != listPersona.get(j).getIdNumber()) {
                    System.out.println("ERROR! EL TITULAR NO EXISTE O EL NUMERO DE IDENTIFICACION ES ERRADO");
                } else {
                    System.out.println("EL TITULAR TIENE LAS SIGUIENTES CUENTAS ASOCIADAS:");
                    for (int i = 0; i < listCuenta.size(); i++) {
                        this.formQueryAccount(listCuenta.get(i));
                    }
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("*ERROR*");
            System.out.println("INGRESE SOLO VALORES NUMERICOS");
        }
    }

    public void formQueryAccount(Cuenta accountQuery) {
        System.out.println("LOS DATOS DE LA CUENTA ALMACENADA SON:");
        System.out.println("NUMERO DE CUENTA: " + accountQuery.getAccountNumber());
        System.out.println("TIPO DE CUENTA: " + accountQuery.getAccountType());
        System.out.println("SALDO: " + accountQuery.getBalance());
        System.out.println("ESTADO DE LA CUENTA: " + accountQuery.getAccountStatus());
    }

    public boolean validateOnlyCharacter(String word) {
        if (!word.matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }
    }

}
