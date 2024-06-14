package test;

import datos.PersonaDAO;
import domain.Persona;

import java.util.List;

public class main {

    public static void main(String[] args) {

        PersonaDAO personaDAO = new PersonaDAO();

        List<Persona> personaList = personaDAO.seleccionar();



        //Insertando un nue3vo objeto de tipo persona

        Persona nuevaPersona = new Persona(1,"cogenwe ","Ment","ment@gmail.com","3211233221");
        int personaEliminda = 4;
        personaDAO.delete(personaEliminda);

        personaList.forEach(Persona->{
            System.out.print("id: "+ Persona.getIdPersona());
            System.out.print(" nombre: "+ Persona.getNombre());
            System.out.print(" apellido: "+ Persona.getApellido());
            System.out.print(" correo: "+ Persona.getCorreo());
            System.out.print(" telefono: "+ Persona.getTelefono() + "\n");

        });

    }
}
