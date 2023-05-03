#include "raylib.h"

struct Ball
{
    //Initialize and declare ball position variables
    float x, y;
    float speedX, speedY;
    float radius;

    void Draw()
    {
        DrawCircle(x, y, radius, WHITE);
    }
};

struct Paddle
{
    float x, y;
    float speed;
    float width, height;

    Rectangle getRect()
    {
        return Rectangle{x - width / 2, y - height / 2, 10, 100};
    }

    void Draw()
    {
        DrawRectangleRec(getRect(), WHITE);
    }
};

int main()
{
    //Create window
    InitWindow(800, 600, "Pong");
    SetWindowState(FLAG_VSYNC_HINT);

    Ball ball;

    //Initialize and declare ball position variables
    ball.x = GetScreenWidth() / 2;
    ball.y = GetScreenHeight() / 2;
    ball.radius = 5;

    //Initialize and declare ball speed variables
    ball.speedX = 300;
    ball.speedY = 300;

    //Initialize and declare left paddle variables
    Paddle leftPaddle;
    leftPaddle.x = 50;
    leftPaddle.y = GetScreenHeight() / 2;
    leftPaddle.width = 10;
    leftPaddle.height = 100;
    leftPaddle.speed = 500;

    //Initialize and declare right paddle variables
    Paddle rightPaddle;
    rightPaddle.x = GetScreenWidth() - 50;
    rightPaddle.y = GetScreenHeight() / 2;
    rightPaddle.width = 10;
    rightPaddle.height = 100;
    rightPaddle.speed = 500;

    const char* winnerText = nullptr;

    while (!WindowShouldClose())
    {
        //Syncronize frame rates and start ball movement
        ball.x += ball.speedX * GetFrameTime();
        ball.y += ball.speedY * GetFrameTime();

        //make ball bounce off the top of the screen
        if (ball.y < 0)
        {
            ball.y = 0;
            ball.speedY *= -1;
        }

        //make ball bounce off the bottom of the screen
        if (ball.y > GetScreenHeight())
        {
            ball.y = GetScreenHeight();
            ball.speedY *= -1;
        }

        //controls for left paddle
        if (IsKeyDown(KEY_W))
        {
            leftPaddle.y -= leftPaddle.speed * GetFrameTime();
        }
        if (IsKeyDown(KEY_S))
        {
            leftPaddle.y += leftPaddle.speed * GetFrameTime();
        }

        //controls for right paddle
        if (IsKeyDown(KEY_UP))
        {
            rightPaddle.y -= rightPaddle.speed * GetFrameTime();
        }
        if (IsKeyDown(KEY_DOWN))
        {
            rightPaddle.y += rightPaddle.speed * GetFrameTime();
        }

        //use built-in raylib function to determine the collision 
        //of the ball and the right paddle and if that ball hits the paddle
        //the speed of the ball is reversed
        if (CheckCollisionCircleRec(Vector2{ ball.x, ball.y }, ball.radius, rightPaddle.getRect()))
        {
            if (ball.speedX > 0)
            {
                ball.speedX *= -1.1f;
                ball.speedY = (ball.y - rightPaddle.y) / (rightPaddle.height / 2) * -ball.speedX;
            }
        }

        //use built-in raylib function to determine the collision 
        //of the ball and the left paddle and if that ball hits the paddle
        //the speed of the ball is reversed
        if (CheckCollisionCircleRec(Vector2{ ball.x, ball.y }, ball.radius, leftPaddle.getRect()))
        {
            if (ball.speedX < 0)
            {
                ball.speedX *= -1.1f;
                ball.speedY = (ball.y - leftPaddle.y) / (leftPaddle.height / 2) * ball.speedX;
            }
        }

        //determine the winner and change winnerText accordingly
        if (ball.x < 0)
        {
            winnerText = "Right Player Wins!";
        }
        if (ball.x > GetScreenWidth())
        {
            winnerText = "Left Player Wins!";
        }

        if (winnerText)
        {
            int textWidth = MeasureText(winnerText, 60);
            DrawText(winnerText, GetScreenWidth() / 2 - textWidth / 2, GetScreenHeight() / 2 - 30, 60, YELLOW);
        }

        if (winnerText && IsKeyPressed(KEY_SPACE))
        {
            ball.x = GetScreenWidth() / 2;
            ball.y = GetScreenHeight() / 2;
            ball.speedX = 300;
            ball.speedY = 300;
            winnerText = nullptr;
        }

        BeginDrawing();
        ClearBackground(BLACK);

        ball.Draw();
        leftPaddle.Draw();
        rightPaddle.Draw();

        DrawFPS(10, 10);
        EndDrawing();
    }

    CloseWindow();

    return 0;
}