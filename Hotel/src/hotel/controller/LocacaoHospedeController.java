package hotel.controller;

import hotel.model.LocacaoHospede;
import hotel.repository.LocacaoHospedeRepository;
import java.util.List;

public class LocacaoHospedeController extends BaseController<LocacaoHospede>{
    
    public List<LocacaoHospede> getReadId(int pCodigo) {
        try {
            return LocacaoHospedeRepository.readId(pCodigo);
        } catch (Exception ex) {
            LoggerController.log(this.getClass(), ex);
        }
        return null;
    }
}
