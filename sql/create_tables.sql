-- Creating RoutingEventHistory --
CREATE TABLE IF NOT EXISTS RoutingEventRepository (
    routingId INT NOT NULL,
    supergwId INT NOT NULL,
    dateTimeReceived TIMESTAMP NOT NULL,
    dateTimeSent TIMESTAMP NOT NULL,
    receiver varchar(128) NOT NULL,
    sent varchar(128) NOT NULL,
    payload varchar(2056)
);

--    private int id;
--
--    @Column(name = "DateReceived", nullable = false)
--    private LocalDateTime dateReceived;
--
--    @Column(name = "DateSent", nullable = false)
--    private LocalDateTime dateSent;
--
--    @Column(name = "Sender", nullable = false)
--    private String sender; //This should be some number or key
--
--    @Column(name = "Receiver", nullable = false)
--    private String receiver; //This should be some number or key
--
--    @Column(name = "Payload")
--    // JSON Object serialized into a string
--    private String payload;
)