package interfaces;

import entitys.RequestDTO;
import entitys.ResponseDTO;

public interface Back {
    ResponseDTO gateway(RequestDTO requestDto);
}
