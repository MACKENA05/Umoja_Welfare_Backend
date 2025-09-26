package com.monicah.Umoja_Welfare.Controller;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.MemberDTO;
import com.monicah.Umoja_Welfare.Service.RegisterWelfareMembersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "api/v1/auth")
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private RegisterWelfareMembersService registerWelfareMembers;
    @PostMapping("/member_register")
    public BaseApiResponse registerMember (@RequestBody @Valid MemberDTO memberDTO) throws Exception{
        return registerWelfareMembers.RegisterMembers(memberDTO);

    }
    @PutMapping("/updateMember/{phoneNumber}")
    public ResponseEntity<BaseApiResponse> updateMembers (
            @PathVariable String phoneNumber,
             @RequestBody @Valid MemberDTO memberDTO) throws Exception {
     BaseApiResponse response = registerWelfareMembers.UpdateMembers(phoneNumber,memberDTO);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/memberlist")
    public ResponseEntity<BaseApiResponse> List() throws Exception {
        try {
            BaseApiResponse baseApiResponse = registerWelfareMembers.memberList();
            return ResponseEntity.status(baseApiResponse.getStatus()).body(baseApiResponse);
        }
        catch (Exception e) {
            // Handle exception and return a failed response
            BaseApiResponse errorResponse = BaseApiResponse.builder()
                    .status(500)
                    .message("An error occurred while fetching patient data.")
                    //.errors(List.of(new FieldErrorDTO("unknown", e.getMessage())))
                    .build();
            return ResponseEntity.status(500).body(errorResponse);
        }

    }
}
