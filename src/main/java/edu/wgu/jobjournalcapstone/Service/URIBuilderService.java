package edu.wgu.jobjournalcapstone.Service;

import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class URIBuilderService {

    // Note: Changed Application to be Object - check here if there are issues later
    public URI buildURI(Object object){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(object)
                .toUri();

        return uri;
    }

}
