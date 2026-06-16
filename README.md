# webapp_login

> *A battle-tested, enterprise-grade, cloud-native user management platform.*
>
> *(It's a Java login page that lists users. We don't talk about that.)*

Born in the hallowed halls of OOC coursework and refined over **literally dozens of minutes**, `webapp_login` is the definitive solution for organizations that need to:

- Log in
- See a list of people
- Log out
- Question their life choices

Trusted by **0 Fortune 500 companies** and **at least one homelab**.

## Features

- **Password hashing** — BCrypt, because it's 2018 forever in this repo
- **Session management** — SecurityService does the thing
- **CRUD for users** — register, edit, delete; live dangerously
- **Embedded Tomcat** — no external app server, no problems (many problems)
- **Docker-ready** — ship it to your NAS like a responsible adult

## Architecture

```
[ Browser ] → [ Tomcat in a JAR ] → [ MySQL ] → [ regret ]
```

Microservices were considered. They were rejected. Strongly.

## Quick Start (Docker)

```bash
cp .env.example .env
# edit passwords — seriously, change them
docker compose up -d
```

Open **http://localhost:8080** and behold the login form.

## Homelab Deploy

Images are published to GHCR on every push to `master`:

```
ghcr.io/umi4life/webapp_login:latest
```

On your server:

```bash
docker login ghcr.io
docker compose pull app
docker compose up -d
```

Set `APP_IMAGE` in `.env` to match the lowercase registry path.

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `PORT` | `8080` | App port |
| `DB_HOST` | `localhost` | MySQL host (`db` in compose) |
| `DB_PORT` | `3306` | MySQL port |
| `DB_NAME` | `webapp_login` | Database name |
| `DB_USER` | `muic` | Database user |
| `DB_PASSWORD` | `ooc` | Database password — change this |

## Local Dev (no Docker)

Requirements: Java 8, Maven, MySQL with the `credentials` table (see `docker/init-db.sql`).

```bash
mvn package
java -jar target/login-webapp-1.0-SNAPSHOT.jar
```

Or run `./deploy.sh` if you enjoy `sudo iptables` and living on the edge.

## License

No license. Use at your own risk. The authors accept no responsibility for homelab incidents, broken auth flows, or existential dread.

---

*Built with ☕, stubbornness, and a concerning amount of JSP.*
