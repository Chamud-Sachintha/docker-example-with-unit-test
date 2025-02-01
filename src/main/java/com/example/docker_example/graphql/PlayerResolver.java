package com.example.docker_example.graphql;

import com.example.docker_example.models.Player;
import com.example.docker_example.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PlayerResolver {

    @Autowired
    private PlayerRepository playerRepository;

    @MutationMapping
    public Player registerPlayer(@Argument(name = "playerName") String playerName, @Argument(name = "playerEmail") String playerEmail) {
        Player player = new Player();

        player.setName(playerName);
        player.setEmail(playerEmail);

        return playerRepository.save(player);
    }

    @QueryMapping
    public Optional<Player> getPlayer(@Argument Long id) {
        return playerRepository.findById(id);
    }
}
