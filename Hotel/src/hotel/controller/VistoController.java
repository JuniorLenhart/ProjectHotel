package hotel.controller;

import hotel.model.Novidade;
import hotel.model.Usuario;
import hotel.model.Visto;
import hotel.repository.VistoRepository;

public class VistoController extends BaseController<Visto> {

    public Visto getReadByUserAndNew(Usuario user, Novidade news) {
        try {
            return VistoRepository.readByUserAndNew(user, news);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
}
