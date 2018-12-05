import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
//import java.util.Date;

public class iFace {

    static Scanner read = new Scanner(System.in);

    static String alerta = null;
    static int base_amigos = 200;
    static int total_amigos = 0;
    static int index_amigos = 0;
    static int base_usuarios = 200;
    static int index_usuarios = 0;
    static int total_usuarios = 0;
    static Usuario[] usuarios = new Usuario[base_usuarios];

    static public void main(String[] args) {
        char opcao = '0';

        while(opcao != '5') {
            menu();
            opcao = read.next().charAt(0);

            switch (opcao) {
                case '1':                                        // Conta
                    System.out.println("\nCONTA");
                    conta();
                    break;
                case '2':                                        // Amigos
                    System.out.println("\nAMIGOS");
                    //menu_amigos();
                    break;
                case '3':                                        // Listar usuários
                    System.out.println("\nLISTAR USUARIOS");
                    listar_usuarios();
                    break;
                case '4':                                        // Listar amigos
                    System.out.println("\nLISTAR AMIGOS");
                    //listar_amigos();
                    break;
                case '5':
                    System.exit(0);
                    break;
                default:
                    alerta = "Opção inválida!\n";
                    mostrar_alerta();
            }
        }
    }

    public static void mostrar_alerta() {
        if (alerta != null) {
            System.out.println("\n" + alerta);
            alerta = null;
        }
    }

    static public void menu() {
        System.out.println("\n1 - Minha conta");
        System.out.println("2 - Amigos");
        System.out.println("3 - Listar usuários");
        System.out.println("4 - Listar amigos");
        System.out.println("5 - Sair");
        System.out.print(">> ");
    }

    static public void menu_conta() {
        System.out.println("\n1 - Criar conta");
        System.out.println("2 - Apagar conta");
        System.out.println("3 - Editar conta");
        System.out.println("4 - Voltar");
        System.out.print(">> ");
    }

    static public  void menu_editar() {
        System.out.println("\n1 - Login");
        System.out.println("2 - Nome de usuario");
        System.out.println("3 - Senha");
        System.out.println("4 - Nome completo");
        System.out.println("5 - Idade");
        System.out.println("6 - Aniversário");
        System.out.println(("7 - Voltar"));
        System.out.print(">> ");
    }

    static public void listar_usuarios() {
        if (total_usuarios == 0) {
            System.out.println("\nSem registros");
        } else {
            for (Usuario usuario : usuarios) {
                if (usuario == null) break;
                if (usuario.ativo == true) print_usuario(usuario);
            }
        }
    }

    static public void conta() {
        char opcao_conta = '0';

        while(opcao_conta != 4) {
            menu_conta();
            opcao_conta = read.next().charAt(0);

            switch(opcao_conta) {
                case '1':
                    criar_conta();
                    break;
                case '2':
                    apagar_conta();
                    break;
                case '3':
                    editar_conta();
                    break;
                case '4':
                    return;
                default:
                    alerta = "Opção inválida!\n";
                    mostrar_alerta();
            }
        }
    }

    static public Usuario buscar_usuario() {
        char opcao_busca = '0';

        while(opcao_busca != 3) {
            System.out.println("\n1 - Buscar pelo nome de usuario");
            System.out.println("2 - Buscar pelo nome completo");
            System.out.println("3 - Voltar");
            System.out.print(">> ");
            opcao_busca = read.next().charAt(0);

            switch(opcao_busca) {
                case '1':
                    System.out.print("\nNome do usuario: ");
                    read.nextLine();
                    String aux_nome_usuario = read.nextLine();

                    for(Usuario usuario : usuarios) {
                        if(usuario == null) return null;
                        if(!usuario.ativo) continue;
                        if(usuario.nome_usuario.toLowerCase().equals(aux_nome_usuario.toLowerCase())) {
                            return usuario;
                        }
                    }
                    break;

                case '2':
                    System.out.print("\nNome completo: ");
                    read.nextLine();
                    String aux_nome_completo = read.nextLine();

                    for(Usuario usuario : usuarios) {
                        if(usuario == null) return null;
                        if(!usuario.ativo) continue;
                        if(usuario.nome_usuario.toUpperCase().equals(aux_nome_completo.toUpperCase())) {
                            return usuario;
                        }
                    }
                    break;

                case '3':
                    return null;

                default:
                    alerta = "Opção inválida!\n";
                    mostrar_alerta();
            }
        }

        return null;
    }

    static public void editar_conta() {
        Usuario usuario = buscar_usuario();
        char editar = '0';

        if(usuario != null) {
            System.out.print("\nUsuário encontrado:\n");
            print_usuario(usuario);
        }
        else {
            System.out.print("\nUsuário não encontrado!\n");
            return;
        }

        menu_editar();
        editar = read.next().charAt(0);

        switch(editar) {
            case '1':
                System.out.print("\nNovo login: ");
                read.nextLine();
                usuario.login = read.nextLine();

                alerta = "Login alterado com sucesso!";
                mostrar_alerta();
                break;

            case '2':
                System.out.print("\nNovo nome de usuário: ");
                read.nextLine();
                usuario.nome_usuario = read.nextLine();

                alerta = "Nome de usuário alterado com sucesso!";
                mostrar_alerta();
                break;

            case '3':
                System.out.print("\nInsira senha antiga: ");
                read.nextLine();
                String aux_senha = read.nextLine();

                if(!usuario.senha.toUpperCase().equals(aux_senha.toUpperCase())) {
                    alerta = "Senhas não batem!";
                    mostrar_alerta();
                    break;
                }

                System.out.print("\nNova senha: ");
                String nova_senha = read.nextLine();
                System.out.print("Confirme nova senha: ");
                String confirma_nova_senha = read.nextLine();

                if(!nova_senha.toUpperCase().equals(confirma_nova_senha.toUpperCase())) {
                    alerta = "Senhas não batem!";
                    mostrar_alerta();
                    break;
                }

                usuario.senha = nova_senha;
                alerta = "Senha alterada com sucesso!";
                mostrar_alerta();
                break;

            case '4':
                System.out.print("\nNovo nome completo: ");
                read.nextLine();
                usuario.nome_completo = read.nextLine();

                alerta = "Nome completo alterado com sucesso!";
                mostrar_alerta();
                break;

            case '5':
                System.out.print("\nNova idade: ");
                usuario.idade = read.nextInt();

                alerta = "Idade alterada com sucesso!";
                mostrar_alerta();
                break;

            case '6':
                System.out.print("\nNovo aniversário: ");
                read.nextLine();
                usuario.aniversario = read.nextLine();

                alerta = "Aniversário alterado com sucesso!";
                mostrar_alerta();
                break;

            case '7':
                return;

            default:
                alerta = "Opção inválida!";
                mostrar_alerta();
        }


    }

    static public void apagar_conta() {
        Usuario usuario = buscar_usuario();
        char apagar = '0';

        if(usuario != null) {
            System.out.print("Usuário encontrado:\n");
            print_usuario(usuario);
            System.out.print("\nDeseja apagar? (y/n) ");
            apagar = read.next().charAt(0);
        }
        else {
            System.out.print("\nUsuário não encontrado!\n");
            return;
        }

        if(apagar == 'y' || apagar == 'Y') {
            usuario.ativo = false;
            total_usuarios--;
            alerta = "Usuário apagado com sucesso!\n";
            mostrar_alerta();
        }
        else return;

    }

    static public void criar_conta() {
        Usuario usuario = new Usuario();

        System.out.print("\nLogin: ");
        read.nextLine();
        usuario.login = read.nextLine();

        System.out.print("Senha: ");
        usuario.senha = read.nextLine();

        System.out.print("Nome de usuário: ");
        usuario.nome_usuario = read.nextLine();

        System.out.print("Nome completo: ");
        usuario.nome_completo = read.nextLine();

        System.out.print("Idade: ");
        usuario.idade = read.nextInt();

        System.out.print("Aniversario: (dd/mm/aaaa) ");
        read.nextLine();
        usuario.aniversario = read.nextLine();

        usuario.ativo = true;

        salvar_usuario(usuario);
        print_usuario(usuario);
    }

    static public void salvar_usuario(Usuario usuario) {
        if (index_amigos == usuarios.length) {
            Usuario[] usuarios_aux = new Usuario[index_usuarios + base_usuarios];

            for (int i = 0; i < index_usuarios; i++) {
                usuarios_aux[i] = usuarios[i];
            }
            usuarios = usuarios_aux;
        }

        usuarios[index_usuarios++] = usuario;
        ++total_usuarios;
    }

    static public void print_usuario(Usuario usuario) {
        System.out.println("\nLogin: " + usuario.login);
        System.out.println("Nome de usuário: " + usuario.nome_usuario);
        System.out.println("Nome completo: " + usuario.nome_completo);
        System.out.println("Idade: " + usuario.idade);
        System.out.println("Aniversário: " + usuario.aniversario);
    }

}
