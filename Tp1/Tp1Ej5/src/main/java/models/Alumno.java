package models;

import exceptions.MateriaRepetidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alumno {
    private DatosPersonal datosPersonal;
    private int legajo;
    private String nombre;
    private String apellido;
    private List<Materia> materias;

    public Alumno(int legajo, String nombre, String apellido, String calle, String ciudad, String telefono, String email) {
        this.datosPersonal = new DatosPersonal(calle, ciudad, telefono, email);
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materias = new ArrayList<>();
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public int getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return legajo == alumno.legajo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
    }

    @Override
    public String toString() {
        String mensaje = "Alumno{" +
                "legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'';
        for (Materia m : this.materias) {
            mensaje += m;
        }
        return mensaje + "}";
    }

    public Materia agregarMateria(Materia materia) throws MateriaRepetidaException {
        if (materias.contains(materia)) {
            throw new MateriaRepetidaException();
        }
        this.materias.add(materia);
        return materia;
    }

    //Inner Class DatosPersonal
    public class DatosPersonal {
        private String calle;
        private String ciudad;
        private String telefono;
        private String email;

        public DatosPersonal(String calle, String ciudad, String telefono, String email) {
            this.calle = calle;
            this.ciudad = ciudad;
            this.telefono = telefono;
            this.email = email;
        }

        public String getCalle() {
            return calle;
        }

        public String getCiudad() {
            return ciudad;
        }

        public String getTelefono() {
            return telefono;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "calle='" + calle + '\'' +
                    ", ciudad='" + ciudad + '\'' +
                    ", telefono=" + telefono +
                    ", email='" + email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DatosPersonal that)) return false;
            return Objects.equals(calle, that.calle) && Objects.equals(ciudad, that.ciudad) && Objects.equals(telefono, that.telefono) && Objects.equals(email, that.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(calle, ciudad, telefono, email);
        }
    }
}
