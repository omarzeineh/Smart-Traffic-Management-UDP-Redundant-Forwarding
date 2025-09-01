# Smart Traffic Management – UDP Redundant Forwarding

This repository contains a minimal UDP-based client–forwarder–server setup for a Smart Traffic Management System. It demonstrates high availability, fault tolerance, and redundancy by forwarding the same UDP packet from a traffic sensor (client) to multiple redundant servers via a UDP forwarder.

> Scenario: Multiple IoT traffic sensors at intersections send real-time metrics (vehicle count, average speed, light status...) to a centralized control center using UDP. A data forwarder replicates each packet to two or more servers so processing continues even if one server fails.

---

## Components

- `Client_p1` – Simulates an IoT traffic sensor. Sends UDP datagrams with synthetic traffic data.
- `Forwarder_p1` / `Forwarder_p2` – UDP forwarders that receive packets from clients and forward (replicate) them to one or more servers.
- `Server_p1` – UDP monitoring server that listens for incoming traffic data and prints/logs it.
- `Sensor` – Simple POJO/model to synthesize/format sensor readings (e.g., vehicle count, avg speed, light status).

> Java package name is set to `Smart_Traffic` (renamed from the original long assignment identifier for clarity). No logic has been changed—only the `package` declaration line.

---

## Ports

- Default UDP forwarder/server port: 9100 (you may switch to 9101 if needed).
- Ensure the chosen port is open on your OS/firewall.

---

## Build & Run

### 1) Compile (from repo root)
```bash
javac -d out $(find . -name "*.java")
```
This compiles sources into the `out/` directory.

### 2) Run the servers (in separate terminals)

```bash
# Terminal A — Server 1
java -cp out Smart_Traffic.Server_p1 9100

# Terminal B — Server 2 (if applicable)
java -cp out Smart_Traffic.Server_p1 9101
```
> If `Server_p1` internally binds to a fixed port, omit the CLI arg and just run `java -cp out Smart_Traffic.Server_p1`. Otherwise, pass a port value as shown above.

### 3) Run the forwarder(s)

```bash
# Terminal C — Forwarder that listens from client on 9100 and forwards to both servers
java -cp out Smart_Traffic.Forwarder_p1
# (If a second forwarder variant is provided)
java -cp out Smart_Traffic.Forwarder_p2
```
> The forwarder receives one packet and forwards copies to both servers to ensure redundancy.

### 4) Run the traffic sensor client

```bash
# Terminal D — Client (sensor)
java -cp out Smart_Traffic.Client_p1
```
The client will emit synthetic readings (from `Sensor`) and send them over UDP to the forwarder.

---

## Expected Console Output (illustrative)

```
Traffic Sensor:
  sending: {intersection=A1, vehicles=37, avg=42Km/h, status=Green}

Traffic Server 1:
  received: {intersection=A1, vehicles=37, avg=42Km/h, status=Green}

Traffic Server 2:
  received: {intersection=A1, vehicles=37, avg=42Km/h, status=Green}
```
Your actual printouts may differ depending on how the classes format their logs.

---

## Notes

- This project is intentionally minimal to focus on UDP message paths and redundancy.
- For real deployments, consider adding reconnection logic, back-pressure, structured logging, and message signing/validation.
- If running on Windows, allow Java through the firewall or temporarily disable blocking for the chosen ports.
- If you need to adjust host/port values, check the top of `Client_p1` / `Forwarder_p1` / `Server_p1` for constants or argument parsing.

---

## Repository Structure

```
.
├── Client_p1.java
├── Forwarder_p1.java
├── Forwarder_p2.java
├── Sensor.java
├── Server_p1.java
└── README.md
```

---

## Attribution

This implementation fulfills Programming Assignment 1 – Question 1 requirements: UDP clients (sensors), redundant servers, and a UDP forwarder that replicates packets for fault tolerance, redundancy, and high availability.
