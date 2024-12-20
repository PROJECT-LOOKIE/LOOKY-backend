package com.likelion.lookie.controller.file;

import com.nimbusds.oauth2.sdk.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.likelion.lookie.common.dto.PresignedUrlRequest;
import com.likelion.lookie.common.dto.PresignedUrlResponse;
import com.likelion.lookie.common.exception.ApplicationResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "File", description = "File API")
public interface FileControllerDocs {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(summary = "Presigned URL 요청", description = "파일 업로드를 위한 presigned URL을 생성하는 API")
    ApplicationResponse<PresignedUrlResponse> uploadPhoto(
            @RequestBody PresignedUrlRequest presignedUrlRequest
    );
}
