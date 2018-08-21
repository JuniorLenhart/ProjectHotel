package hotel.controller;

import hotel.model.Parametro;
import hotel.repository.ParametroRepository;

public class ParametroController {

    public void loadClass() {
        new Parametro(ParametroRepository.read());
    }
}
