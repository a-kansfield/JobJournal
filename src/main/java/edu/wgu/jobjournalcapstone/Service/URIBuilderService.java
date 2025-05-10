package edu.wgu.jobjournalcapstone.Service;

import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class URIBuilderService {

    public URI buildURI(Application application){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(application)
                .toUri();

        return uri;
    }
}
