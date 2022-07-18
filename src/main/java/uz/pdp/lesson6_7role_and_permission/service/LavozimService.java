package uz.pdp.lesson6_7role_and_permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson6_7role_and_permission.entity.Lavozim;
import uz.pdp.lesson6_7role_and_permission.payload.ApiResponce;
import uz.pdp.lesson6_7role_and_permission.payload.LavozimDto;
import uz.pdp.lesson6_7role_and_permission.payload.UserDto;
import uz.pdp.lesson6_7role_and_permission.repository.LavozimRepository;

@Service
public class LavozimService {

    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponce addLavozim(LavozimDto lavozimDto) {
        if (lavozimRepository.existsByName(lavozimDto.getName())) {
            return new ApiResponce("this name already exist", false);
        } else {
            Lavozim lavozim = new Lavozim(

                    lavozimDto.getName(),
                    lavozimDto.getHuquqList(),
                    lavozimDto.getDescription());

            lavozimRepository.save(lavozim);
            return new ApiResponce("saved", true);
        }
    }

    public ApiResponce editLavozim(long id, LavozimDto lavozimDto) {
        return new ApiResponce("true", true);
    }
}
