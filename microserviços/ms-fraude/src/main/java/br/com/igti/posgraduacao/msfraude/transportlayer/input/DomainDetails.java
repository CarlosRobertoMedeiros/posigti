package br.com.igti.posgraduacao.msfraude.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainDetails {
    private String domain;
    private String tld;
    private Boolean registered;
    private Boolean disposable;
    private Boolean free;
    private Boolean custom;
    private Boolean dmarc_enforced;
    private Boolean spf_strict;
    private Boolean valid_mx;
    private Boolean accept_all;
    private Boolean suspicious_tld;
    private Boolean website_exists;
    private LocalDateTime created;
    private LocalDateTime updated;
}
