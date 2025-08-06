package mvc1.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /*
    회원 목록 조회 GET /mapping/users
     */
    @GetMapping
    public String users(){
        return "get users";
    }

    /*
    회원 등록 POST /mapping/users
     */
    @PostMapping
    public String assUser(){
        return "post user";
    }

    /*
    회원 아이디로 조회 GET /mapping/users/{userId}
     */
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "get userId="+userId;
    }

    /*
    회원 정보 수정 Patch /mapping/users/{userId}
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId="+userId;
    }

    /*
    회원 삭제 DELETE /mapping/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId="+userId;
    }
}
