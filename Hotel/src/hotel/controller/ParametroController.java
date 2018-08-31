package hotel.controller;

import hotel.model.Parametro;
import hotel.repository.ParametroRepository;

public class ParametroController extends BaseController<Parametro> {

    public void loadClass() {
        try {
            new Parametro(ParametroRepository.read());
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
    }
}
