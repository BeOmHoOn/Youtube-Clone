package com.clone.metube.video.dtos;

import lombok.Data;

@Data
public class VideoUploadRequest {
    /*
    id bigint [pk, increment]
    local_author_id bigint
    soical_author_id bigint
    location varchar
    title varchar
    description text
    length integer
    create_at timestamp
     */
    private String email;
    private String title;
    private String description;
}
