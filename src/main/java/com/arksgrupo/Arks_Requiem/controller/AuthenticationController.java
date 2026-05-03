package com.arksgrupo.Arks_Requiem.controller;
import com.arksgrupo.Arks_Requiem.dto.AuthResponseDTO;
import com.arksgrupo.Arks_Requiem.dto.LoginDTO;
import com.arksgrupo.Arks_Requiem.dto.RegisterDTO;
import com.arksgrupo.Arks_Requiem.dto.UsuarioDTO;
import com.arksgrupo.Arks_Requiem.model.Usuario;
import com.arksgrupo.Arks_Requiem.repository.UsuarioRepository;
import com.arksgrupo.Arks_Requiem.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth") // A rota será http://localhost:8080/auth
public class AuthenticationController{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;
    // [AJUSTE] Removido @Autowired fantasma que estava aqui e causava erro no LoginDTO

    // ENDPOINT DE LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO data){
        // O Spring tenta autenticar o login e a senha
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
            var auth = authenticationManager.authenticate(usernamePassword);

            var usuario = (Usuario) auth.getPrincipal();
            String token = tokenService.generateToken(usuario);

            UsuarioDTO usuarioDTO =new UsuarioDTO(usuario.getId(),usuario.getLogin(),usuario.getRole());
 
            // Se der certo, por enquanto retornamos OK
            return ResponseEntity.ok(new AuthResponseDTO(token,usuarioDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro Interno: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }


    // ENDPOINT DE CADASTRO (Aqui definimos se é ADMIN ou USER)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data){
    if(repository.findByLogin(data.getLogin()) != null){
        return ResponseEntity.badRequest().build();
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
    Usuario newUser = new Usuario(data.getLogin(),encryptedPassword,data.getRole());

    repository.save(newUser);

    return ResponseEntity.ok().build();
    }
}