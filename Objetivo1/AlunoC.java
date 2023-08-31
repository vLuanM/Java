package Controller;
import Model.Aluno;
import java.util.*;

public class AlunoC {
    public static void main(String[]args){

        List<Aluno> alunos = new ArrayList<>();

        Aluno aluno1 = new Aluno();
        aluno1.setId(1);
        aluno1.setCpf(123456789);
        aluno1.setNome("Anderson");
        aluno1.setSobrenome("Silva");
        aluno1.setEmail("anderson.silva@gmail.com");

        Aluno aluno2 = new Aluno();
        aluno2.setId(2);
        aluno2.setCpf(987654321);
        aluno2.setNome("Maria");
        aluno2.setSobrenome("Souza");
        aluno2.setEmail("maria.souza@gmail.com");

        Aluno aluno3 = new Aluno(3, 456789123, "Pedro", "Andrades", "pedro.fernandes@gmail.com");
        Aluno aluno4 = new Aluno(4, 789123456, "Ana", "Madeira", "ana.madeirara@gmail.com");
        Aluno aluno5 = new Aluno(5, "Lucas");
        Aluno aluno6 = new Aluno(6, "Mariana");

        alunos.add(aluno1);
        alunos.add(aluno2);
        alunos.add(aluno3);
        alunos.add(aluno4);
        alunos.add(aluno5);
        alunos.add(aluno6);

        List<Aluno> listaAlunos = new ArrayList<>();
        Map<Integer, Aluno> mapaAlunos = new TreeMap<>();

        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);
        listaAlunos.add(aluno3);
        listaAlunos.add(aluno4);
        listaAlunos.add(aluno5);
        listaAlunos.add(aluno6);

        mapaAlunos.put(aluno1.getId(), aluno1);
        mapaAlunos.put(aluno2.getId(), aluno2);
        mapaAlunos.put(aluno3.getId(), aluno3);
        mapaAlunos.put(aluno4.getId(), aluno4);
        mapaAlunos.put(aluno5.getId(), aluno5);
        mapaAlunos.put(aluno6.getId(), aluno6);

        System.out.println("Lista de Alunos:");
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
        }
        System.out.println("Mapa de Alunos:");
        for (Aluno aluno : mapaAlunos.values()) {
            System.out.println(aluno);
        }

        Aluno alunoLista = listaAlunos.stream().filter(a -> a.getId() == 5).findFirst().orElse(null);
        if (alunoLista != null) {
            System.out.println("Aluno de id=5 Lista: " + alunoLista);
        }
        Aluno alunoMapa = mapaAlunos.get(5);
        if (alunoMapa != null) {
            System.out.println("Aluno de id=5 Mapa: " + alunoMapa);
        }
        Collections.sort(listaAlunos, (a1, a2) -> Integer.compare(a2.getId(), a1.getId()));

        System.out.println("Lista de Alunos em ordem decrescente:");
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
        }

        aluno1.setId(11);
        aluno1.setCpf(111111111);
        aluno1.setNome("Ricardo");
        aluno1.setSobrenome("Bragantins");
        aluno1.setEmail("ricardo.bragantins@gmail.com");

        aluno2.setId(22);
        aluno2.setCpf(222222222);
        aluno2.setNome("Clara");
        aluno2.setSobrenome("Mendronha");
        aluno2.setEmail("clara.mendronha@gmail.com");

        System.out.println(aluno1.getId() + " " + aluno1.getCpf() + " " + aluno1.getNome() + " " + aluno1.getSobrenome() + " " + aluno1.getEmail());
        System.out.println(aluno2.getId() + " " + aluno2.getCpf() + " " + aluno2.getNome() + " " + aluno2.getSobrenome() + " " + aluno2.getEmail());

    }

}

