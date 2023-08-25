-- Creating RoutingEventHistory --
CREATE TABLE IF NOT EXISTS routing_event_history (
    routing_id INT GENERATED ALWAYS AS IDENTITY,
    supergw_id INT NOT NULL,
    datetime_received TIMESTAMP NOT NULL,
    datetime_sent TIMESTAMP NOT NULL,
    receiver varchar(128) NOT NULL,
    sender varchar(128) NOT NULL,
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